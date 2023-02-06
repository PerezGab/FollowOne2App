package com.example.gabbinete.followone2.di

import com.example.gabbinete.followone2.api.ApiDataSource
import com.example.gabbinete.followone2.api.ApiService
import com.example.gabbinete.followone2.repo.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val API_URL = "https://ergast.com/api/f1/"

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

//private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun retrofitProvider(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(API_URL)
        .build()

    @Provides
    @Singleton
    fun okHttpClientProvider(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor {
                val url = it.request().url

                val lastEncodedPathSegment =
                    url.encodedPathSegments[url.encodedPathSegments.lastIndex]

                val modifiedLastEncodedPathSegment = "$lastEncodedPathSegment.json"

                val urlJsonAppended =
                    url.newBuilder().removePathSegment(url.encodedPathSegments.lastIndex)
                        .addEncodedPathSegments(modifiedLastEncodedPathSegment).build()

                val requestJsonAppended = it.request().newBuilder().url(urlJsonAppended).build()

                it.proceed(requestJsonAppended)
            }.build()
    }

    @Provides
    @Singleton
    fun apiCallProvider(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun remoteDataSourceProvider(apiDataSource: ApiDataSource): RemoteDataSource = apiDataSource
}

