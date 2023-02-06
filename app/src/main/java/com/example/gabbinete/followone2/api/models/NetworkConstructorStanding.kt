package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import com.example.gabbinete.followone2.api.models.NetworkConstructor
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkConstructorStanding(
    val position: String,
    val positionText: String,
    val points: String,
    val wins: String,
    @SerializedName("Constructor") val constructor: NetworkConstructor
) : Parcelable
