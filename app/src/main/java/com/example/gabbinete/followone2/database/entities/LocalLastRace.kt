package com.example.gabbinete.followone2.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gabbinete.followone2.domain.GrandPrix

@Entity(tableName = "last_race")
class LocalLastRace(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val season: String,
    val round: String,
    val name: String
) {
    fun toDomainGrandPrix(): GrandPrix {
        return GrandPrix(
            season,
            round,
            null,
            name,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }
}