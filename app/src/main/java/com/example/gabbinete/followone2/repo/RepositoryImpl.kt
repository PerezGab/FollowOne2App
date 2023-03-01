package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriver
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {
    override suspend fun getCurrentDrivers(): List<LocalDriver> =
        remoteDataSource.getCurrentDrivers()

    override suspend fun getCurrentSeasonDriverStandings(): List<LocalDriverStandings> =
        remoteDataSource.getCurrentSeasonDriverStandings()

    override suspend fun getCurrentSeasonConstructorStandings(): List<LocalConstructorStandings> =
        remoteDataSource.getCurrentSeasonConstructorStandings()

    override suspend fun getCurrentSeasonRaces(): List<LocalGrandPrix> =
        remoteDataSource.getCurrentSeasonRaces()

    override suspend fun getLastRace() =
        remoteDataSource.getLastRace()


    override suspend fun getRace(season: String, round: String): LocalGrandPrix =
        remoteDataSource.getRace(season, round)
}