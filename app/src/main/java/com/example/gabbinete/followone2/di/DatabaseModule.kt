package com.example.gabbinete.followone2.di

import android.app.Application
import androidx.room.Room
import com.example.gabbinete.followone2.database.F1Dao
import com.example.gabbinete.followone2.database.F1Database
import com.example.gabbinete.followone2.util.Converters
import com.example.gabbinete.followone2.util.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application): F1Dao =
        Room.databaseBuilder(app, F1Database::class.java, "follow-one-db")
            .addTypeConverter(Converters(GsonParser(Gson())))
            .addMigrations(F1Database.migration1To2)
            .build().f1Dao()
}