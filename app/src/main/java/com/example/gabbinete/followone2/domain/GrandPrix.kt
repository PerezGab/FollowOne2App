package com.example.gabbinete.followone2.domain

import com.example.gabbinete.followone2.api.models.QualifyingResult
import com.example.gabbinete.followone2.api.models.RaceResult
import com.example.gabbinete.followone2.api.models.Session
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix


class GrandPrix(
    val season: String,
    val round: String?,
    val url: String?,
    val raceName: String,
    val circuit: Circuit?,
    val date: String?,
    val time: String?,
    val raceResults: List<RaceResult>?,
    val qualifyingResults: List<QualifyingResult>?,
    val firstPractice: Session?,
    val secondPractice: Session?,
    val thirdPractice: Session?,
    val qualifying: Session?,
    val sprint: Session?
) {
    companion object {
        fun postSeason(): GrandPrix {
            return GrandPrix(
                "",
                null,
                null,
                "Season has finished",
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

    fun toLocalGrandPrix(): LocalGrandPrix {
        return LocalGrandPrix(
            season,
            round,
            url,
            raceName,
            circuit?.toLocalCircuit(),
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



