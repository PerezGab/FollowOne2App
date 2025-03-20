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
) : Parcelable {

    companion object {
        val defaultDriver = Driver(
            "driver", "0", "driver", "", "Default", "Driver", "", "Argentinian"
        )
    }

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
            "Argentinian " -> R.drawable.flag_arg
            "Argentine" -> R.drawable.flag_arg
            "Italian" -> R.drawable.flag_ita
            "New Zealander" -> R.drawable.flag_nzl
            "Brazilian" -> R.drawable.flag_bra
            else -> null
        }
}