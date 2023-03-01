package com.example.gabbinete.followone2.domain

import com.example.gabbinete.followone2.database.entities.LocalCircuit
import com.example.gabbinete.followone2.database.entities.LocalLocation

data class Circuit(
    val circuitId: String,
    val url: String,
    val circuitName: String,
    val location: Location
) {
    fun toLocalCircuit(): LocalCircuit {
        return LocalCircuit(circuitId, url, circuitName, location.toLocalLocation())
    }
}

data class Location(
    val lat: String,
    val _long: String,
    val locality: String,
    val country: String
) {
    fun toLocalLocation(): LocalLocation {
        return LocalLocation(lat, _long, locality, country)
    }
}