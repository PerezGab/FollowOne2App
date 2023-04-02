package com.example.gabbinete.followone2.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gabbinete.followone2.api.models.QualifyingResult
import com.example.gabbinete.followone2.api.models.RaceResult
import com.example.gabbinete.followone2.api.models.Session
import com.example.gabbinete.followone2.domain.GrandPrix

@Entity(tableName = "grand_prix")
data class LocalGrandPrix(
    val season: String,
    val round: String,
    val url: String?,
    val raceName: String,
    val circuit: LocalCircuit?,
    val date: String?,
    val time: String?,
    val raceResults: List<RaceResult>?,
    val qualifyingResults: List<QualifyingResult>?,
    val firstPractice: Session?,
    val secondPractice: Session?,
    val thirdPractice: Session?,
    val qualifying: Session?,
    val sprint: Session?,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) {
    fun toDomainGrandPrix(): GrandPrix {
        return GrandPrix(
            season,
            round,
            url,
            raceName,
            circuit?.toDomainCircuit(),
            date,
            time,
            raceResults,
            qualifyingResults,
            firstPractice,
            secondPractice,
            thirdPractice,
            qualifying,
            sprint
        )
    }
}