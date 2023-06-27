package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.api.models.NetworkRaceResult
import com.example.gabbinete.followone2.api.models.QualifyingResult
import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix
import com.example.gabbinete.followone2.database.entities.LocalLastRace

interface LocalDataSource {
    suspend fun getDriversStandings(): List<LocalDriverStandings>
    suspend fun upsertDriverStandings(standings: List<LocalDriverStandings>)
    suspend fun getConstructorStandings(): List<LocalConstructorStandings>
    suspend fun upsertConstructorStandings(standings: List<LocalConstructorStandings>)
    suspend fun getSeasonRaces(): List<LocalGrandPrix>
    suspend fun upsertSeasonRaces(races: List<LocalGrandPrix>)
    suspend fun clearSeasonRaces()
    suspend fun upsertLastRace(race: LocalLastRace)
    suspend fun getLastRace(): List<LocalLastRace>
    suspend fun getRaceByRound(round: String): LocalGrandPrix
    suspend fun updateRaceResultWithRound(round: String, raceResults: List<NetworkRaceResult>?)
    suspend fun updateQualyResultWithRound(round: String, qualifyingResult: List<QualifyingResult>?)
}