package com.example.gabbinete.followone2.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "constructor_standings")
data class LocalConstructorStandings(
    @PrimaryKey
    val position: String,
    val positionText: String,
    val points: String,
    val wins: String,
    val constructor: LocalConstructor
)