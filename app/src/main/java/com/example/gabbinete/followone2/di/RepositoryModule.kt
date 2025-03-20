package com.example.gabbinete.followone2.di

import android.content.Context
import com.example.gabbinete.followone2.api.ApiDataSource
import com.example.gabbinete.followone2.database.RoomDataSource
import com.example.gabbinete.followone2.repo.LocalDataSource
import com.example.gabbinete.followone2.repo.RemoteDataSource
import com.example.gabbinete.followone2.repo.Repository
import com.example.gabbinete.followone2.repo.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun remoteDataSourceProvider(apiDataSource: ApiDataSource): RemoteDataSource = apiDataSource

    @Provides
    @Singleton
    fun localDataSourceProvider(roomDataSource: RoomDataSource): LocalDataSource = roomDataSource

    @Provides
    @Singleton
    fun repositoryProvider(@ApplicationContext context: Context, api: RemoteDataSource, dao: LocalDataSource): Repository =
        RepositoryImpl(context, api, dao)
}