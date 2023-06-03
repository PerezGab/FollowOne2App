package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Repository {
//    suspend fun getCurrentDrivers(updateData: Boolean): List<LocalDriver>
    fun getCurrentSeasonDriverStandings(updateData: Boolean): Flow<Response<List<DriverStandings>>>
    fun getCurrentSeasonConstructorStandings(updateData: Boolean): Flow<Response<List<ConstructorStandings>>>
    fun getCurrentSeasonRaces(updateData: Boolean): Flow<Response<List<GrandPrix>>>
    fun getLastRace(updateData: Boolean): Flow<Response<List<GrandPrix>>>
    fun getRaceByRound(round: String): Flow<Response<GrandPrix>>
    suspend fun updateRaceResultsByRound(round: String)
    suspend fun updateSeasonRaces()
    suspend fun updateDriverStandings()
    suspend fun updateConstructorStandings()
    suspend fun updateLastRace()
    suspend fun dataStoredCompleted()
    suspend fun areTablesEmpty(): Boolean
    val isDataStored: StateFlow<Boolean>
}