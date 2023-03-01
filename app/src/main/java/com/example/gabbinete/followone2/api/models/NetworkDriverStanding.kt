package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.util.toLocalConstructorList
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkDriverStanding(
    val position: String,
    val positionText: String,
    val points: String,
    val wins: String,
    @SerializedName("Driver") val networkDriver: NetworkDriver,
    @SerializedName("Constructors") val constructors: List<NetworkConstructor>
) : Parcelable {
    fun toLocalDriverStandings(): LocalDriverStandings {
        return LocalDriverStandings(
            position,
            positionText,
            points,
            wins,
            networkDriver.toLocalDriver(),
            constructors.toLocalConstructorList()
        )
    }
}
