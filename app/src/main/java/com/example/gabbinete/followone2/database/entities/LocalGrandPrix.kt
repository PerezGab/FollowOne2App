package com.example.gabbinete.followone2.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gabbinete.followone2.api.models.NetworkRaceResult
import com.example.gabbinete.followone2.api.models.NetworkSession
import com.example.gabbinete.followone2.api.models.QualifyingResult
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.util.toDomainRaceResultsList

@Entity(tableName = "grand_prix")
data class LocalGrandPrix(
    val season: String,
    val round: String,
    val url: String?,
    val raceName: String,
    val circuit: LocalCircuit?,
    val date: String?,
    val time: String?,
    val raceResults: List<NetworkRaceResult>?,
    val qualifyingResults: List<QualifyingResult>?,
    val firstPractice: NetworkSession?,
    val secondPractice: NetworkSession?,
    val thirdPractice: NetworkSession?,
    val qualifying: NetworkSession?,
    val sprint: NetworkSession?,
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
            raceResults?.toDomainRaceResultsList(),
            qualifyingResults,
            firstPractice?.toDomainFp1(),
            secondPractice?.toDomainFp2(),
            thirdPractice?.toDomainFp3(),
            qualifying?.toDomainQualy(),
            sprint?.toDomainSprint()
        )
    }
}