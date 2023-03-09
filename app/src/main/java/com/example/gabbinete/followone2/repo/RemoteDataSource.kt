package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.database.entities.*

interface RemoteDataSource {
    suspend fun getCurrentDrivers(): List<LocalDriver>
    suspend fun getCurrentSeasonDriverStandings(): List<LocalDriverStandings>
    suspend fun getCurrentSeasonConstructorStandings(): List<LocalConstructorStandings>
    suspend fun getCurrentSeasonRaces(): List<LocalGrandPrix>
    suspend fun getLastRace(): LocalLastRace
    suspend fun getRace(round: String): LocalGrandPrix
}