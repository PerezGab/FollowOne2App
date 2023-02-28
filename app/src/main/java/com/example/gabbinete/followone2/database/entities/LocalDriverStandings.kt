package com.example.gabbinete.followone2.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "driver_standings")
data class LocalDriverStandings(
    @PrimaryKey val position: String,
    val positionText: String,
    val points: String,
    val wins: String,
    val localDriver: LocalDriver,
    val localConstructors: List<LocalConstructor>
)