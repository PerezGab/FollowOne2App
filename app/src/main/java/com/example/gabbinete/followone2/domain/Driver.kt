package com.example.gabbinete.followone2.domain

import android.os.Parcelable
import com.example.gabbinete.followone2.R
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Driver(
    val driverId: String,
    val permanentNumber: String,
    val code: String,
    val url: String,
    val givenName: String,
    val familyName: String,
    val dateOfBirth: String,
    val nationality: String
):Parcelable {
    @IgnoredOnParcel
    val mapFlag: Int? =
        when (nationality) {
            "Australian" -> R.drawable.flag_aus
            "British" -> R.drawable.flag_gbr
            "Canadian" -> R.drawable.flag_can
            "Chinese" -> R.drawable.flag_chn
            "Danish" -> R.drawable.flag_den
            "Dutch" -> R.drawable.flag_ned
            "Finnish" -> R.drawable.flag_fin
            "French" -> R.drawable.flag_fra
            "German" -> R.drawable.flag_ger
            "Japanese" -> R.drawable.flag_jpn
            "Mexican" -> R.drawable.flag_mex
            "Monegasque" -> R.drawable.flag_mon
            "Spanish" -> R.drawable.flag_spa
            "Thai" -> R.drawable.flag_tha
            "American" -> R.drawable.flag_usa
            else -> null
        }
}