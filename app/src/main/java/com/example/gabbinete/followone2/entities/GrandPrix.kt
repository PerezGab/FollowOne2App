package com.example.gabbinete.followone2.entities

import com.example.gabbinete.followone2.api.models.QualifyingResult
import com.example.gabbinete.followone2.api.models.RaceResult
import com.example.gabbinete.followone2.api.models.Session


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
}



