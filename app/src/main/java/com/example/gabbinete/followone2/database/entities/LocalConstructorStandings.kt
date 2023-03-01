package com.example.gabbinete.followone2.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gabbinete.followone2.domain.ConstructorStandings

@Entity(tableName = "constructor_standings")
data class LocalConstructorStandings(
    @PrimaryKey
    val position: String,
    val positionText: String,
    val points: String,
    val wins: String,
    val constructor: LocalConstructor
) {
    fun toDomainConstructorStandings(): ConstructorStandings {
        return ConstructorStandings(
            position,
            positionText,
            points,
            wins,
            constructor.toDomainConstructor()
        )
    }
}