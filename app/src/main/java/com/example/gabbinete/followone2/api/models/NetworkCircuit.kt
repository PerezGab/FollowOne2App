package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import com.example.gabbinete.followone2.database.entities.LocalCircuit
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkCircuit(
    val circuitId: String,
    val url: String,
    val circuitName: String,
    @SerializedName("Location") val location: NetworkLocation
) : Parcelable {
    fun toLocalCircuit(): LocalCircuit {
        return LocalCircuit(
            circuitId = circuitId,
            url = url,
            circuitName = circuitName,
            location = location.toLocalLocation()
        )
    }
}