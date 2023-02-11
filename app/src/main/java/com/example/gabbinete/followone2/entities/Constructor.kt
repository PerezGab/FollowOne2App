package com.example.gabbinete.followone2.entities

import com.example.gabbinete.followone2.R

data class Constructor(
    val constructorId: String,
    val url: String,
    val name: String,
    val nationality: String
) {
    val logo: Int? =
        when (name) {
            "Red Bull" -> R.drawable.red_bull_logo
            "Ferrari" -> R.drawable.logo_ferrari
            "Mercedes" -> R.drawable.logo_mercedes
            "Alpine F1 Team" -> R.drawable.logo_alpine
            "McLaren" -> R.drawable.logo_mclaren
            "Alfa Romeo" -> R.drawable.logo_alfa_romeo
            "Aston Martin" -> R.drawable.logo_aston_martin
            "Haas F1 Team" -> R.drawable.logo_haas
            "AlphaTauri" -> R.drawable.logo_alpha_tauri
            "Williams" -> R.drawable.williams_logo
            else -> null
        }
}