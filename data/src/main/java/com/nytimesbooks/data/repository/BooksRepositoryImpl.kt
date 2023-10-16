package com.nytimesbooks.data.repository

import com.nytimesbooks.data.local.dataSource.BooksLocalDataSource
import com.nytimesbooks.data.mappers.books.BooksMapper
import com.nytimesbooks.data.mappers.categories.CategoriesMapper
import com.nytimesbooks.data.remote.base.NetworkState
import com.nytimesbooks.data.remote.dataSource.BooksRemoteDataSource
import com.nytimesbooks.domain.base.ErrorEntity
import com.nytimesbooks.domain.base.ErrorHandler
import com.nytimesbooks.domain.base.UIState
import com.nytimesbooks.domain.models.books.Books
import com.nytimesbooks.domain.models.categories.Categories
import com.nytimesbooks.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val api: BooksRemoteDataSource,
    private val db: BooksLocalDataSource,
    private val errorHandler: ErrorHandler
): BooksRepository {

    override suspend fun getCategories(): Flow<UIState<Categories>> = flow {
        emit(UIState.Loading)

        val dbResult = db.getCategories() ?: emptyList()
        emit(UIState.Success(data = CategoriesMapper.fromDbEntityToDomain(dbResult)))

        when (val apiResult = api.getCategories()) {
            is NetworkState.Success -> {
                val data = apiResult.data ?: run {
                    return@flow emit(UIState.Failure(error = ErrorEntity.Unknown))
                }
                val categories = CategoriesMapper.fromDtoToDomain(data)
                emit(UIState.Success(data = categories))

                val dbEntities = CategoriesMapper.fromDtoToDbEntity(data)
                db.updateCategories(dbEntities)
            }
            is NetworkState.Failure -> if (dbResult.isEmpty()) {
                emit(UIState.Failure(error = errorHandler.getError(apiResult.statusCode)))
            }
            else -> {}
        }
    }

    override suspend fun getBooks(list: String): Flow<UIState<Books>> = flow {
        emit(UIState.Loading)

        val dbResult = db.getBooks(list)
        dbResult?.let { emit(UIState.Success(data = BooksMapper.fromDbEntityToDomain(it))) }

        when (val apiResult = api.getBooks(list)) {
            is NetworkState.Success -> {
                val data = apiResult.data ?: run {
                    return@flow emit(UIState.Failure(error = ErrorEntity.Unknown))
                }
                val books = BooksMapper.fromDtoToDomain(data)
                emit(UIState.Success(data = books))

                val dbEntities = BooksMapper.fromDtoToDbEntity(data)
                db.updateBooks(dbEntities)
            }
            is NetworkState.Failure -> {
                if (dbResult == null) {
                    emit(UIState.Failure(error = errorHandler.getError(apiResult.statusCode)))
                }
            }
            else -> {}
        }
    }
}