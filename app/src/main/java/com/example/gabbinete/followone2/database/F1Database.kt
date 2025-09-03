package com.example.gabbinete.followone2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriver
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix
import com.example.gabbinete.followone2.database.entities.LocalLastRace
import com.example.gabbinete.followone2.util.Converters

@Database(
    entities = [LocalDriverStandings::class, LocalGrandPrix::class, LocalLastRace::class, LocalConstructorStandings::class, LocalDriver::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class F1Database : RoomDatabase() {

    abstract fun f1Dao(): F1Dao

    companion object {
        val migration1To2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS drivers (
                        driverId TEXT NOT NULL,
                        permanentNumber TEXT NOT NULL,
                        code TEXT NOT NULL,
                        url TEXT NOT NULL,
                        givenName TEXT NOT NULL,
                        familyName TEXT NOT NULL,
                        dateOfBirth TEXT NOT NULL,
                        nationality TEXT NOT NULL,
                        PRIMARY KEY(driverId)
                    )
                    """.trimIndent()
                )
            }
        }
    }
}