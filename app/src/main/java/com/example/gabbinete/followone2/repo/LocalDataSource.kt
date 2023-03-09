package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix
import com.example.gabbinete.followone2.database.entities.LocalLastRace

interface LocalDataSource {
//    suspend fun getDrivers(): List<LocalDriver>
//    suspend fun insertDrivers(drivers: List<LocalDriver>)
    suspend fun getDriversStandings(): List<LocalDriverStandings>
    suspend fun insertDriverStandings(standings: List<LocalDriverStandings>)
    suspend fun getConstructorStandings(): List<LocalConstructorStandings>
    suspend fun insertConstructorStandings(standings: List<LocalConstructorStandings>)
    suspend fun getSeasonRaces(): List<LocalGrandPrix>
    suspend fun insertSeasonRaces(races: List<LocalGrandPrix>)
    suspend fun clearSeasonRaces()
    suspend fun insertLastRace(race: LocalLastRace)
    suspend fun getLastRace(): LocalLastRace
}