package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import com.example.gabbinete.followone2.domain.Location
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkLocation(
    val lat: String,
    @SerializedName("long") val _long: String,
    val locality: String,
    val country: String
) : Parcelable {
    fun toDomainLocation(): Location {
        return Location(
            lat = lat,
            _long = _long,
            locality = locality,
            country = country
        )
    }
}
