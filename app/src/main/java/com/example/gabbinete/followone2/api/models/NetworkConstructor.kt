package com.example.gabbinete.followone2.api.models

import android.os.Parcelable
import com.example.gabbinete.followone2.database.entities.LocalConstructor
import com.example.gabbinete.followone2.domain.Constructor
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkConstructor(
    val constructorId: String,
    val url: String,
    val name: String,
    val nationality: String
) : Parcelable {
    fun toLocalConstructor(): LocalConstructor {
        return LocalConstructor(
            constructorId, url, name, nationality
        )
    }

    fun toDomainConstructor(): Constructor {
        return Constructor(
            constructorId, url, name, nationality
        )
    }
}
