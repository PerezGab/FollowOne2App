package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.api.models.NetworkRaceResult
import com.example.gabbinete.followone2.api.models.QualifyingResult
import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriver
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix
import com.example.gabbinete.followone2.database.entities.LocalLastRace

interface RemoteDataSource {
    suspend fun getCurrentDrivers(): List<LocalDriver>
    suspend fun getCurrentSeasonDriverStandings(): List<LocalDriverStandings>
    suspend fun getCurrentSeasonConstructorStandings(): List<LocalConstructorStandings>
    suspend fun getCurrentSeasonRaces(): List<LocalGrandPrix>
    suspend fun getLastRace(): LocalLastRace
//    suspend fun getLastResults(): List<NetworkRaceResult>?
    suspend fun getLastQualy(): List<QualifyingResult>?
    suspend fun getRace(round: String): LocalGrandPrix
    suspend fun getRaceResults(round: String): List<NetworkRaceResult>?
    suspend fun getQualyResults(round: String): List<QualifyingResult>?
}