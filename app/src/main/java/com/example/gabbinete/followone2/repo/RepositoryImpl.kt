package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {
//    override suspend fun getCurrentDrivers(updateData: Boolean): List<LocalDriver> {
//        return if (!updateData) {
//            localDataSource.getDrivers()
//        }
//        remoteDataSource.getCurrentDrivers()
//    }

    override suspend fun getCurrentSeasonDriverStandings(updateData: Boolean): List<LocalDriverStandings> {
        return if (!updateData) {
            localDataSource.getDriversStandings()
        } else {
            try {
                val standings = remoteDataSource.getCurrentSeasonDriverStandings()
                localDataSource.insertDriverStandings(standings)

                localDataSource.getDriversStandings()
            } catch (e: HttpException) {
                localDataSource.getDriversStandings()
            } catch (e: IOException) {
                localDataSource.getDriversStandings()
            }
        }
    }

    override suspend fun getCurrentSeasonConstructorStandings(updateData: Boolean): List<LocalConstructorStandings> {
        return if (!updateData) {
            localDataSource.getConstructorStandings()
        } else {
            try {
                val standings = remoteDataSource.getCurrentSeasonConstructorStandings()
                localDataSource.insertConstructorStandings(standings)

                localDataSource.getConstructorStandings()
            } catch (e: HttpException) {
                localDataSource.getConstructorStandings()
            } catch (e: IOException) {
                localDataSource.getConstructorStandings()
            }
        }
    }

    override suspend fun getCurrentSeasonRaces(updateData: Boolean): List<LocalGrandPrix> {
        return if (!updateData) {
            localDataSource.getSeasonRaces()
        } else {
            try {
                val seasonRaces = remoteDataSource.getCurrentSeasonRaces()
                localDataSource.clearSeasonRaces()
                localDataSource.insertSeasonRaces(seasonRaces)

                localDataSource.getSeasonRaces()
            } catch (e: HttpException) {
                localDataSource.getSeasonRaces()
            } catch (e: IOException) {
                localDataSource.getSeasonRaces()
            }
        }
    }

    override suspend fun getLastRace(updateData: Boolean): LocalGrandPrix {
        return if (!updateData) {
            val round = localDataSource.getLastRace().round.toInt() - 1
            localDataSource.getSeasonRaces()[round]
        } else {
            try {
                val lastRace = remoteDataSource.getLastRace()
                localDataSource.insertLastRace(lastRace)
                val round = localDataSource.getLastRace().round.toInt() - 1

                localDataSource.getSeasonRaces()[round]
            } catch (e: HttpException) {
                localDataSource.getSeasonRaces()[localDataSource.getLastRace().round.toInt() - 1]
            } catch (e: IOException) {
                localDataSource.getSeasonRaces()[localDataSource.getLastRace().round.toInt() - 1]
            }
        }
    }


    override suspend fun getRace(round: String): LocalGrandPrix {
        val localSeasonRaces = localDataSource.getSeasonRaces()
        return if (localSeasonRaces.isNotEmpty()) {
            localDataSource.getSeasonRaces()[round.toInt() - 1]
        } else {
            try {
                val seasonRaces = remoteDataSource.getCurrentSeasonRaces()
                localDataSource.insertSeasonRaces(seasonRaces)

                localDataSource.getSeasonRaces()[round.toInt() - 1]
            } catch (e: HttpException) {
                localDataSource.getSeasonRaces()[round.toInt() - 1]
            } catch (e: IOException) {
                localDataSource.getSeasonRaces()[round.toInt() - 1]
            }
        }
    }
}
