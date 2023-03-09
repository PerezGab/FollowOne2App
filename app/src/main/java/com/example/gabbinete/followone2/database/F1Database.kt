package com.example.gabbinete.followone2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix
import com.example.gabbinete.followone2.database.entities.LocalLastRace
import com.example.gabbinete.followone2.util.Converters

@Database(
    entities = [LocalDriverStandings::class, LocalGrandPrix::class, LocalLastRace::class, LocalConstructorStandings::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class F1Database : RoomDatabase() {

    abstract fun f1Dao(): F1Dao
}