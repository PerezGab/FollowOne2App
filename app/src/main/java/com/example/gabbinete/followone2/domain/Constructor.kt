package com.example.gabbinete.followone2.domain

import android.os.Parcelable
import com.example.gabbinete.followone2.R
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Constructor(
    val constructorId: String,
    val url: String,
    val name: String,
    val nationality: String
): Parcelable {
    @IgnoredOnParcel
    val logo: Int? =
        when (name) {
            "Red Bull" -> R.drawable.logo_redbull
            "Ferrari" -> R.drawable.logo_ferrari
            "Mercedes" -> R.drawable.logo_mercedes
            "Alpine F1 Team" -> R.drawable.logo_alpine
            "McLaren" -> R.drawable.logo_mclaren
            "Aston Martin" -> R.drawable.logo_astonmartin
            "Haas F1 Team" -> R.drawable.logo_haas
            "Williams" -> R.drawable.logo_williams
            "RB F1 Team" -> R.drawable.logo_racingbulls
            "Audi" -> R.drawable.logo_audi
            "Cadillac F1 Team" -> R.drawable.logo_cadillac
            else -> null
        }
}