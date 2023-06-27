package com.example.gabbinete.followone2.di

import com.example.gabbinete.followone2.api.ApiService
import com.example.gabbinete.followone2.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun retrofitProvider(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.API_URL)
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
}

