package com.example.nytimesbooks.di.data

import android.content.Context
import androidx.room.Room
import com.nytimesbooks.data.local.BooksDatabase
import com.nytimesbooks.data.local.dao.BooksDao
import com.nytimesbooks.data.local.dataSource.BooksLocalDataSource
import com.nytimesbooks.data.local.dataSource.BooksLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesBooksRoomDatabase(@ApplicationContext applicationContext: Context): BooksDatabase =
        Room
            .databaseBuilder(applicationContext, BooksDatabase::class.java, "books_database.db")
            .build()

    @Provides
    @Singleton
    fun providesBooksLocalDataSource(booksDao: BooksDao): BooksLocalDataSource =
        BooksLocalDataSourceImpl(booksDao)

    @Provides
    @Singleton
    fun providesBooksDao(appDatabase: BooksDatabase) = appDatabase.getBooksDao()
}