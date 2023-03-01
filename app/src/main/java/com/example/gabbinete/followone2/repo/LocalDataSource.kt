package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix

interface LocalDataSource {
    suspend fun getDriversStandings(): List<LocalDriverStandings>
    suspend fun getConstructorStandings(): List<LocalConstructorStandings>
    suspend fun getSeasonRaces(): List<LocalGrandPrix>
    suspend fun insertSeasonRaces(races: List<LocalGrandPrix>)
}