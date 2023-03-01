package com.example.gabbinete.followone2.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gabbinete.followone2.domain.Driver

@Entity(tableName = "drivers")
data class LocalDriver(
    @PrimaryKey val driverId: String,
    val permanentNumber: String,
    val code: String,
    val url: String,
    val givenName: String,
    val familyName: String,
    val dateOfBirth: String,
    val nationality: String
) {
    fun toDomainDriver(): Driver {
        return Driver(
            driverId, permanentNumber, code, url, givenName, familyName, dateOfBirth, nationality
        )
    }
}