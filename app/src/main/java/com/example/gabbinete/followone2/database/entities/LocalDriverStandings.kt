package com.example.gabbinete.followone2.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.util.toDomainConstructor

@Entity(tableName = "driver_standings")
data class LocalDriverStandings(
    @PrimaryKey val position: String,
    val positionText: String,
    val points: String,
    val wins: String,
    val localDriver: LocalDriver,
    val localConstructors: List<LocalConstructor>
) {
    fun toDomainDriverStandings(): DriverStandings {
        return DriverStandings(
            position,
            positionText,
            points,
            wins,
            localDriver.toDomainDriver(),
            localConstructors.toDomainConstructor()
        )
    }
}