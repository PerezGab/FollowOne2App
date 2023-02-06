package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.entities.Driver
import com.example.gabbinete.followone2.entities.GrandPrix
import com.example.gabbinete.followone2.entities.SeasonStandings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getCurrentDrivers(): List<Driver> =
        withContext(Dispatchers.IO) { remoteDataSource.getCurrentDrivers() }

    suspend fun getCurrentSeasonDriverStandings(): List<SeasonStandings> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getCurrentSeasonDriverStandings()
        }

    suspend fun getCurrentSeasonConstructorStandings(): List<SeasonStandings> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getCurrentSeasonConstructorStandings()
        }

    suspend fun getCurrentSeasonRaces(): List<GrandPrix> = withContext(Dispatchers.IO) {
        remoteDataSource.getCurrentSeasonRaces()
    }

    suspend fun getLastRace(): GrandPrix = withContext(Dispatchers.IO) {
        remoteDataSource.getLastRace()
    }

    suspend fun getRace(season: String, round: String): GrandPrix = withContext(Dispatchers.IO) {
        remoteDataSource.getRace(season, round)
    }
}

interface RemoteDataSource {
    suspend fun getCurrentDrivers(): List<Driver>
    suspend fun getCurrentSeasonDriverStandings(): List<SeasonStandings>
    suspend fun getCurrentSeasonConstructorStandings(): List<SeasonStandings>
    suspend fun getCurrentSeasonRaces(): List<GrandPrix>
    suspend fun getLastRace(): GrandPrix
    suspend fun getRace(season: String, round: String): GrandPrix
}