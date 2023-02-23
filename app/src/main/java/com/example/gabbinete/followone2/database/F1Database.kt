package com.example.gabbinete.followone2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gabbinete.followone2.database.entities.LocalConstructor
import com.example.gabbinete.followone2.database.entities.LocalDriver
import com.example.gabbinete.followone2.util.Converters

@Database(entities = [LocalDriver::class, LocalConstructor::class, ], version = 1)
@TypeConverters(Converters::class)
abstract class F1Database : RoomDatabase(){

    abstract fun f1Dao(): F1Dao
}