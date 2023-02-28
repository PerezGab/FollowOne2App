package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.domain.Driver
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.domain.GrandPrix
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun getCurrentDrivers(): List<Driver> = remoteDataSource.getCurrentDrivers()

    suspend fun getCurrentSeasonDriverStandings(): List<DriverStandings> =
        remoteDataSource.getCurrentSeasonDriverStandings()

    suspend fun getCurrentSeasonConstructorStandings(): List<ConstructorStandings> =
        remoteDataSource.getCurrentSeasonConstructorStandings()

    suspend fun getCurrentSeasonRaces(): List<GrandPrix> =
        remoteDataSource.getCurrentSeasonRaces()

    suspend fun getLastRace(): GrandPrix =
        remoteDataSource.getLastRace()


    suspend fun getRace(season: String, round: String): GrandPrix =
        remoteDataSource.getRace(season, round)
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
    suspend fun insertSeasonRaces(races: List<GrandPrix>)
//    suspend fun getLastRace(): GrandPrix
}