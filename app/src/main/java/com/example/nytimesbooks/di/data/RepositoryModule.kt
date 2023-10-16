package com.example.nytimesbooks.di.data

import com.nytimesbooks.data.local.dataSource.BooksLocalDataSource
import com.nytimesbooks.data.remote.base.ErrorHandlerImpl
import com.nytimesbooks.data.remote.dataSource.BooksRemoteDataSource
import com.nytimesbooks.data.repository.BooksRepositoryImpl
import com.nytimesbooks.domain.base.ErrorHandler
import com.nytimesbooks.domain.repository.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesBooksRepository(
        remoteDataSource: BooksRemoteDataSource,
        localDataSource: BooksLocalDataSource,
        errorHandler: ErrorHandler
    ): BooksRepository =
        BooksRepositoryImpl(
            api = remoteDataSource,
            db = localDataSource,
            errorHandler = errorHandler
        )

    @Provides
    @Singleton
    fun providesErrorHandler(): ErrorHandler = ErrorHandlerImpl()
}