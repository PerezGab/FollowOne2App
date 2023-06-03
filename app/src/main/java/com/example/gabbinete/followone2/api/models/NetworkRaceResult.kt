package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import com.example.gabbinete.followone2.domain.RaceResults
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkRaceResult(
    val number: String,
    val position: String,
    val positionText: String,
    val points: String,
    @SerializedName("Driver") val networkDriver: NetworkDriver,
    @SerializedName("Constructor") val constructor: NetworkConstructor,
    val grid: String,
    val laps: String,
    val status: String,
    @SerializedName("Time") val time: Time,
    @SerializedName("FastestLap") val fastestLap: FastestLap
) : Parcelable {
    fun toDomainRaceResult(): RaceResults {
        return RaceResults(
            number,
            position,
            positionText,
            points,
            networkDriver.toDomainDriver(),
            constructor.toDomainConstructor(),
            grid,
            laps,
            status,
            time,
            fastestLap
        )
    }
}
