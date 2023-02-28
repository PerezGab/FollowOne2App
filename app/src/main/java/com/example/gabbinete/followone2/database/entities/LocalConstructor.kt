package com.example.gabbinete.followone2.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gabbinete.followone2.domain.Constructor

@Entity(tableName = "constructors")
data class LocalConstructor(
    @PrimaryKey val constructorId: String,
    val url: String,
    val name: String,
    val nationality: String
) {
    fun toDomainConstructor(): Constructor {
        return Constructor(
            constructorId, url, name, nationality
        )
    }
}