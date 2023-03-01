package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriver
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix

interface RemoteDataSource {
    suspend fun getCurrentDrivers(): List<LocalDriver>
    suspend fun getCurrentSeasonDriverStandings(): List<LocalDriverStandings>
    suspend fun getCurrentSeasonConstructorStandings(): List<LocalConstructorStandings>
    suspend fun getCurrentSeasonRaces(): List<LocalGrandPrix>
    suspend fun getLastRace(): LocalGrandPrix
    suspend fun getRace(season: String, round: String): LocalGrandPrix
}