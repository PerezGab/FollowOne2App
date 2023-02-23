package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.domain.Driver
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.domain.GrandPrix
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getCurrentDrivers(): List<Driver> =
        withContext(Dispatchers.IO) { remoteDataSource.getCurrentDrivers() }

    suspend fun getCurrentSeasonDriverStandings(): List<DriverStandings> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getCurrentSeasonDriverStandings()
        }

    suspend fun getCurrentSeasonConstructorStandings(): List<ConstructorStandings> =
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
    suspend fun getCurrentSeasonDriverStandings(): List<DriverStandings>
    suspend fun getCurrentSeasonConstructorStandings(): List<ConstructorStandings>
    suspend fun getCurrentSeasonRaces(): List<GrandPrix>
    suspend fun getLastRace(): GrandPrix
    suspend fun getRace(season: String, round: String): GrandPrix
}

interface LocalDataSource {
    suspend fun getDriversStandings(): List<DriverStandings>
    suspend fun getConstructorStandings(): List<ConstructorStandings>
    suspend fun getSeasonRaces(): List<GrandPrix>
//    suspend fun getLastRace(): GrandPrix
}