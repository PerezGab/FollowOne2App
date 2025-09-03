package com.example.gabbinete.followone2.domain

import android.os.Parcelable
import com.example.gabbinete.followone2.R
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Circuit(
    val circuitId: String,
    val url: String,
    val circuitName: String,
    val location: Location
): Parcelable {
    @IgnoredOnParcel
    val layout: Int? =
        when (circuitId) {
            "bahrain" -> R.drawable.circuit_bahrain
            "jeddah" -> R.drawable.circuit_saudi_arabia
            "albert_park" -> R.drawable.circuit_albert_park
            "baku" -> R.drawable.circuit_baku
            "miami" -> R.drawable.circuit_miami
            "monaco" -> R.drawable.circuit_monaco
            "catalunya" -> R.drawable.circuit_spain
            "villeneuve" -> R.drawable.circuit_canada
            "red_bull_ring" -> R.drawable.circuit_autria
            "silverstone" -> R.drawable.circuit_great_britain
            "hungaroring" -> R.drawable.circuit_hungary
            "spa" -> R.drawable.circuit_belgium
            "zandvoort" -> R.drawable.circuit_netherlands
            "monza" -> R.drawable.circuit_monza
            "marina_bay" -> R.drawable.circuit_singapore
            "suzuka" -> R.drawable.circuit_japan
            "losail" -> R.drawable.circuit_qatar
            "americas" -> R.drawable.circuit_austin
            "rodriguez" -> R.drawable.circuit_mexico
            "interlagos" -> R.drawable.circuit_brazil
            "vegas" -> R.drawable.circuit_las_vegas
            "yas_marina" -> R.drawable.circuit_abu_dhabi
            else -> null
        }
}

@Parcelize
data class Location(
    val lat: String,
    val _long: String,
    val locality: String,
    val country: String
): Parcelable {
    @IgnoredOnParcel
    val countryFlag: Int? =
        when (country) {
            "Australia" -> R.drawable.flag_aus
            "UK" -> R.drawable.flag_gbr
            "Canada" -> R.drawable.flag_can
            "China" -> R.drawable.flag_chn
            "Denmark" -> R.drawable.flag_den
            "Netherlands" -> R.drawable.flag_ned
            "Finland" -> R.drawable.flag_fin
            "France" -> R.drawable.flag_fra
            "Germany" -> R.drawable.flag_ger
            "Japan" -> R.drawable.flag_jpn
            "Mexico" -> R.drawable.flag_mex
            "Monaco" -> R.drawable.flag_mon
            "Spain" -> R.drawable.flag_spa
            "Thailand" -> R.drawable.flag_tha
            "USA" -> R.drawable.flag_usa
            "Bahrain" -> R.drawable.flag_brn
            "Saudi Arabia" -> R.drawable.flag_ksa
            "Azerbaijan" -> R.drawable.flag_aze
            "Austria" -> R.drawable.flag_aut
            "Hungary" -> R.drawable.flag_hun
            "Belgium" -> R.drawable.flag_bel
            "Italy" -> R.drawable.flag_ita
            "Singapore" -> R.drawable.flag_sgp
            "Qatar" -> R.drawable.flag_qat
            "Brazil" -> R.drawable.flag_bra
            "UAE" -> R.drawable.flag_uae
            else -> null
        }
}