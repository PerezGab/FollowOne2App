package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import com.example.gabbinete.followone2.domain.Driver
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkDriver (
    val driverId: String,
    val permanentNumber: String,
    val code: String,
    val url: String,
    val givenName: String,
    val familyName: String,
    val dateOfBirth: String,
    val nationality: String
) : Parcelable {
    fun toDomainDriver(): Driver {
        return Driver(
            driverId = driverId,
            permanentNumber = permanentNumber,
            code = code,
            url = url,
            givenName = givenName,
            familyName = familyName,
            dateOfBirth = dateOfBirth,
            nationality = nationality
        )
    }
}


