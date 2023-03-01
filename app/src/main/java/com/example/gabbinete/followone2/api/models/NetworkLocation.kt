package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import com.example.gabbinete.followone2.database.entities.LocalLocation
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkLocation(
    val lat: String,
    @SerializedName("long") val _long: String,
    val locality: String,
    val country: String
) : Parcelable {
    fun toLocalLocation(): LocalLocation {
        return LocalLocation(
            lat = lat,
            long = _long,
            locality = locality,
            country = country
        )
    }
}
