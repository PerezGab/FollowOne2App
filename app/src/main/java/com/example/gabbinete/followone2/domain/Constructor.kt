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
            "Red Bull" -> R.drawable.logo_red_bull
            "Ferrari" -> R.drawable.logo_ferrari
            "Mercedes" -> R.drawable.logo_mercedes
            "Alpine F1 Team" -> R.drawable.logo_alpine
            "McLaren" -> R.drawable.logo_mclaren
            "Alfa Romeo" -> R.drawable.logo_alfa_romeo
            "Aston Martin" -> R.drawable.logo_aston_martin
            "Haas F1 Team" -> R.drawable.logo_haas
            "AlphaTauri" -> R.drawable.logo_alpha_tauri
            "Williams" -> R.drawable.logo_williams
            "Sauber" -> R.drawable.logo_kick_sauber
            "RB F1 Team" -> R.drawable.logo_racing_bulls
            else -> null
        }
}