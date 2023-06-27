package com.example.gabbinete.followone2.repo

import android.util.Log
import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.util.Response
import com.example.gabbinete.followone2.util.toDomainConstructorStandings
import com.example.gabbinete.followone2.util.toDomainDriverStandings
import com.example.gabbinete.followone2.util.toDomainGrandPrix
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val TAG = "RepositoryImpl"

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    private val _isDataStored = MutableStateFlow(false)
    override val isDataStored = _isDataStored.asStateFlow()
    override suspend fun dataStoredCompleted() {
        Log.d(TAG, "dataStoredCompleted() is called")
        val areTablesEmpty = areTablesEmpty()
        _isDataStored.value = !areTablesEmpty
        Log.d(TAG, "isDataStored changed value to ${!areTablesEmpty}")
    }

    override suspend fun areTablesEmpty(): Boolean {
        Log.d(TAG, "areTablesEmpty() is called")
        return (localDataSource.getSeasonRaces().isEmpty()
                && localDataSource.getDriversStandings().isEmpty()
                && localDataSource.getConstructorStandings().isEmpty()
                && localDataSource.getLastRace().isEmpty())
    }

    override fun getCurrentSeasonDriverStandings(updateData: Boolean): Flow<Response<List<DriverStandings>>> =
        flow {
            emit(Response.Loading(isLoading = true))
            if (updateData) {
                emit(
                    Response.Success(
                        localDataSource.getDriversStandings().toDomainDriverStandings()
                    )
                )
            } else try {
                updateDriverStandings()
                emit(
                    Response.Success(
                        localDataSource.getDriversStandings().toDomainDriverStandings()
                    )
                )
            } catch (e: HttpException) {
                emit(
                    Response.Error(
                        localDataSource.getDriversStandings().toDomainDriverStandings(),
                        "Could not reach the server."
                    )
                )
            } catch (e: IOException) {
                emit(
                    Response.Error(
                        localDataSource.getDriversStandings().toDomainDriverStandings(),
                        "Can not access to internet. Check your connection."
                    )
                )
            }
        }

    override fun getCurrentSeasonConstructorStandings(updateData: Boolean): Flow<Response<List<ConstructorStandings>>> =
        flow {
            Log.d(TAG, "updateData is $updateData")
            emit(Response.Loading(isLoading = true))
            if (updateData) {
                emit(
                    Response.Success(
                        localDataSource.getConstructorStandings().toDomainConstructorStandings()
                    )
                )
            } else try {
                updateConstructorStandings()
                emit(
                    Response.Success(
                        localDataSource.getConstructorStandings().toDomainConstructorStandings()
                    )
                )
            } catch (e: HttpException) {
                emit(
                    Response.Error(
                        localDataSource.getConstructorStandings().toDomainConstructorStandings(),
                        "Could not reach the server."
                    )
                )
            } catch (e: IOException) {
                emit(
                    Response.Error(
                        localDataSource.getConstructorStandings().toDomainConstructorStandings(),
                        "Can not access to internet. Check your connection."
                    )
                )
            }
        }

    override fun getCurrentSeasonRaces(updateData: Boolean): Flow<Response<List<GrandPrix>>> =
        flow {
            Log.d(TAG, "updateData is $updateData")
            emit(Response.Loading(isLoading = true))
            if (updateData) {
                Log.d(TAG, "if condition is true")
                updateSeasonRaces()
                emit(Response.Success(localDataSource.getSeasonRaces().toDomainGrandPrix()))
            } else try {
                Log.d(TAG, "if condition is false")
                emit(
                    Response.Success(
                        localDataSource.getSeasonRaces().toDomainGrandPrix()
                    )
                )
            } catch (e: HttpException) {
                emit(
                    Response.Error(
                        localDataSource.getSeasonRaces().toDomainGrandPrix(),
                        "Could not reach the server."
                    )
                )
            } catch (e: IOException) {
                emit(
                    Response.Error(
                        localDataSource.getSeasonRaces().toDomainGrandPrix(),
                        "Can not access to internet. Check your connection."
                    )
                )
            }
        }

    override fun getLastRace(updateData: Boolean): Flow<Response<List<GrandPrix>>> = flow {
        Log.d(TAG, "getLastRace is called by the Repo")
        Log.d(TAG, "updateData is $updateData")
        emit(Response.Loading(isLoading = true))
        val cachedLastRace = localDataSource.getLastRace()[0]
        if (updateData) {
            Log.d(TAG, "if condition is true")
            emit(Response.Success(listOf(localDataSource.getSeasonRaces()[cachedLastRace.round.toInt() - 1].toDomainGrandPrix())))
        } else try {
            Log.d(TAG, "if condition is false")
            Log.d(TAG, "getLastRace try block. [updateLastRace() is called]")
            updateLastRace()
            val updatedRound = localDataSource.getLastRace()[0].round.toInt() - 1
            updateRaceResultsByRound((updatedRound+1).toString())
            updateQualyResultsByRound((updatedRound+1).toString())

            emit(Response.Success(listOf(localDataSource.getSeasonRaces()[updatedRound].toDomainGrandPrix())))
        } catch (e: HttpException) {
            emit(
                Response.Error(
                    listOf(localDataSource.getSeasonRaces()[cachedLastRace.round.toInt() - 1].toDomainGrandPrix()),
                    "Could not reach the server."
                )
            )
        } catch (e: IOException) {
            emit(
                Response.Error(
                    listOf(localDataSource.getSeasonRaces()[cachedLastRace.round.toInt() - 1].toDomainGrandPrix()),
                    "Can not access to internet. Check your connection."
                )
            )
        }
    }

    override fun getRaceByRound(round: String): Flow<Response<GrandPrix>> = flow {
        emit(Response.Loading(isLoading = true))
        val localSeasonRaces = localDataSource.getSeasonRaces()
        if (localSeasonRaces.isNotEmpty()) {
            emit(Response.Success(localDataSource.getSeasonRaces()[round.toInt() - 1].toDomainGrandPrix()))
        }
        try {
//            updateSeasonRaces()
            emit(Response.Success(localDataSource.getSeasonRaces()[round.toInt() - 1].toDomainGrandPrix()))
        } catch (e: HttpException) {
            emit(
                Response.Error(
                    localDataSource.getSeasonRaces()[round.toInt() - 1].toDomainGrandPrix(),
                    "Could not reach the server."
                )
            )
        } catch (e: IOException) {
            emit(
                Response.Error(
                    localDataSource.getSeasonRaces()[round.toInt() - 1].toDomainGrandPrix(),
                    "Can not access to internet. Check your connection."
                )
            )
        }
    }

    override suspend fun updateSeasonRaces() {
        Log.d(TAG, "updateSeasonRaces is called by the Repo")
        val seasonRaces = remoteDataSource.getCurrentSeasonRaces()
        localDataSource.clearSeasonRaces()
        localDataSource.upsertSeasonRaces(seasonRaces)
        Log.d(TAG, "upsertSeasonRaces is inserting $seasonRaces by the Repo")
    }

    override suspend fun updateDriverStandings() {
        Log.d(TAG, "updateDriverStandings is called by the Repo")
        val standings = remoteDataSource.getCurrentSeasonDriverStandings()
        localDataSource.upsertDriverStandings(standings)
    }

    override suspend fun updateConstructorStandings() {
        Log.d(TAG, "updateConstructorStandings is called by the Repo")
        val standings = remoteDataSource.getCurrentSeasonConstructorStandings()
        localDataSource.upsertConstructorStandings(standings)
    }

    override suspend fun updateLastRace() {
        Log.d(TAG, "updateLastRace is called by the Repo")
        val lastRace = remoteDataSource.getLastRace()
        localDataSource.upsertLastRace(lastRace)
    }

    override suspend fun updateRaceResultsByRound(round: String) {
        Log.d(TAG, "updateRaceResultsByRound is called by the Repo. Round updated is $round")
        try {
            val raceResults = remoteDataSource.getRaceResults(round)
            if (raceResults != null) {
                localDataSource.updateRaceResultWithRound(round, raceResults)
            }
        } catch (e: IOException) {
            Log.d(TAG, "Can not access to internet. Error is ${e.message}")
        } catch (e: HttpException) {
            Log.d(TAG, "Could not reach the server. Error is ${e.message}")
        }
    }

    override suspend fun updateQualyResultsByRound(round: String) {
        Log.d(TAG, "updateQualyResultsByRound is called by the Repo. Round updated is $round")
        try {
            val qualyResults = remoteDataSource.getQualyResults(round)
            if (qualyResults != null) {
                localDataSource.updateQualyResultWithRound(round, qualyResults)
            }
        } catch (e: IOException) {
            Log.d(TAG, "Can not access to internet. Error is ${e.message}")
        } catch (e: HttpException) {
            Log.d(TAG, "Could not reach the server. Error is ${e.message}")
        }
    }
}