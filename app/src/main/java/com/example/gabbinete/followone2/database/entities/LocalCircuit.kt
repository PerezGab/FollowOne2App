package com.example.gabbinete.followone2.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import com.example.gabbinete.followone2.domain.Circuit
import com.example.gabbinete.followone2.domain.Location

@Entity
data class LocalCircuit(
    val circuitId: String,
    val url: String,
    val circuitName: String,
    @Embedded(prefix = "location_")
    val location: LocalLocation
) {
    fun toDomainCircuit(): Circuit {
        return Circuit(circuitId, url, circuitName, location.toDomainLocation())
    }

}

data class LocalLocation(
    val lat: String,
    val long: String,
    val locality: String,
    val country: String
) {
    fun toDomainLocation(): Location {
        return Location(lat, long, locality, country)
    }
}