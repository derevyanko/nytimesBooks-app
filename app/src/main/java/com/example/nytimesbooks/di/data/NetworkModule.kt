package com.example.nytimesbooks.di.data

import com.example.nytimesbooks.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nytimesbooks.data.remote.api.BooksApiService
import com.nytimesbooks.data.remote.dataSource.BooksRemoteDataSource
import com.nytimesbooks.data.remote.dataSource.BooksRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBooksRepository(booksApiService: BooksApiService): BooksRemoteDataSource =
        BooksRemoteDataSourceImpl(api = booksApiService)

    @Provides
    @Singleton
    fun provideBooksApiService(retrofit: Retrofit): BooksApiService =
        retrofit.create(BooksApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL_NYTIMES)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    @Singleton
    fun provideGsonConvertorFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideInterceptor() = Interceptor { chain ->
        val originalRequest = chain.request()
        val httpUrl = originalRequest.url.newBuilder().apply {
            addQueryParameter("api-key", BuildConfig.API_KEY_NYTIMES)
        }.build()

        val request = originalRequest.newBuilder().url(httpUrl).build()
        return@Interceptor chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .build()
}