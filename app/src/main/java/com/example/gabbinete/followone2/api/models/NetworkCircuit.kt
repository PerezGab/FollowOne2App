package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import com.example.gabbinete.followone2.domain.Circuit
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkCircuit(
    val circuitId: String,
    val url: String,
    val circuitName: String,
    @SerializedName("Location") val location: NetworkLocation
) : Parcelable {
    fun toDomainCircuit(): Circuit {
        return Circuit(
            circuitId = circuitId,
            url = url,
            circuitName = circuitName,
            location = location.toDomainLocation()
        )
    }
}