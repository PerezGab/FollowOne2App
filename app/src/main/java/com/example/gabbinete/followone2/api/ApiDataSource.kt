package com.example.gabbinete.followone2.api

import android.util.Log
import com.example.gabbinete.followone2.api.models.NetworkRaceResult
import com.example.gabbinete.followone2.api.models.QualifyingResult
import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriver
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix
import com.example.gabbinete.followone2.database.entities.LocalLastRace
import com.example.gabbinete.followone2.repo.RemoteDataSource
import com.example.gabbinete.followone2.util.toLocalConstructorStandings
import com.example.gabbinete.followone2.util.toLocalDriver
import com.example.gabbinete.followone2.util.toLocalDriverStandings
import com.example.gabbinete.followone2.util.toLocalGrandPrixList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "ApiDataSource"


class ApiDataSource @Inject constructor(private val apiService: ApiService) : RemoteDataSource {

    override suspend fun getCurrentDrivers(): List<LocalDriver> = withContext(Dispatchers.IO) {
        apiService.getCurrentDrivers().mRData.driverTable.networkDrivers.toLocalDriver()
    }

    override suspend fun getCurrentSeasonDriverStandings(): List<LocalDriverStandings> =
        withContext(Dispatchers.IO) {
            apiService.getCurrentSeasonDriverStandings().mRDataDriver.standingsTable.standingsLists[0].driverStandings.toLocalDriverStandings()
        }

    override suspend fun getCurrentSeasonConstructorStandings(): List<LocalConstructorStandings> =
        withContext(Dispatchers.IO) {
            apiService.getCurrentSeasonConstructorStandings().mRDataDriver.standingsTable.standingsLists[0].constructorStandings.toLocalConstructorStandings()
        }

    override suspend fun getCurrentSeasonRaces(): List<LocalGrandPrix> =
        withContext(Dispatchers.IO) {
            apiService.getCurrentSeasonRaces().mrData.raceTable.networkGrandPrixes.toLocalGrandPrixList()
        }

    override suspend fun getLastRace(): LocalLastRace = withContext(Dispatchers.IO) {
        Log.d(TAG, "getLastRace calls API.")
        apiService.getLastRace().mrData.raceTable.networkGrandPrixes[0].toLocalLastRace()
    }

//    override suspend fun getLastResults(): List<NetworkRaceResult>? = withContext(Dispatchers.IO) {
//        try {
//            apiService.getLastRace().mrData.raceTable.networkGrandPrixes[0].raceResults
//        } catch (e: Exception) {
//            null
//        }
//    }

    override suspend fun getLastQualy(): List<QualifyingResult>? = withContext(Dispatchers.IO){
        try {
            apiService.getLastQualy().mrData.raceTable.networkGrandPrixes[0].qualifyingResults
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getRace(round: String): LocalGrandPrix =
        withContext(Dispatchers.IO) {
            apiService.getRace(round).mrData.raceTable.networkGrandPrixes[0].toLocalGrandPrix()
        }

    override suspend fun getRaceResults(round: String): List<NetworkRaceResult>? =
        withContext(Dispatchers.IO) {
            try {
                apiService.getRaceResults(round).mrData.raceTable.networkGrandPrixes[0].raceResults
            } catch (e: Exception) {
                null
            }
        }

    override suspend fun getQualyResults(round: String): List<QualifyingResult>? = withContext(Dispatchers.IO){
        try {
            apiService.getQualyResults(round).mrData.raceTable.networkGrandPrixes[0].qualifyingResults
        } catch (e: Exception) {
            null
        }
    }

}