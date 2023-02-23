package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import com.example.gabbinete.followone2.domain.GrandPrix
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Race(
    val season: String,
    val round: String,
    val url: String,
    val raceName: String,
    @SerializedName("Circuit") val circuit: NetworkCircuit,
    val date: String,
    val time: String,
    @SerializedName("Results") val raceResults: List<RaceResult>,
    @SerializedName("QualifyingResults") val qualifyingResults: List<QualifyingResult>,
    @SerializedName("SprintResults") val sprintResults: List<SprintResult>,
    @SerializedName("FirstPractice") val firstPractice: Session,
    @SerializedName("SecondPractice") val secondPractice: Session,
    @SerializedName("ThirdPractice") val thirdPractice: Session,
    @SerializedName("Qualifying") val qualifying: Session,
    @SerializedName("Sprint") val sprint: Session
) : Parcelable {
    fun toDomainGrandPrix(): GrandPrix {
        return GrandPrix(
            season = season,
            round = round,
            url = url,
            raceName = raceName,
            circuit = circuit.toDomainCircuit(),
            date = date,
            time = time,
            raceResults = raceResults,
            qualifyingResults = qualifyingResults,
            firstPractice = firstPractice,
            secondPractice = secondPractice,
            thirdPractice = thirdPractice,
            qualifying = qualifying,
            sprint = sprint
        )
    }
}
