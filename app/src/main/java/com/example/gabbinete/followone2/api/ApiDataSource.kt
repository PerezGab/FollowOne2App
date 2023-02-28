package com.example.gabbinete.followone2.api

import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.domain.Driver
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.repo.RemoteDataSource
import com.example.gabbinete.followone2.util.toDomainConstructorStandings
import com.example.gabbinete.followone2.util.toDomainDriver
import com.example.gabbinete.followone2.util.toDomainDriverStandings
import com.example.gabbinete.followone2.util.toDomainGrandPrixList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ApiDataSource @Inject constructor(private val apiService: ApiService) : RemoteDataSource {

    override suspend fun getCurrentDrivers(): List<Driver> = withContext(Dispatchers.IO) {
        apiService.getCurrentDrivers().mRData.driverTable.networkDrivers.toDomainDriver()
    }

    override suspend fun getCurrentSeasonDriverStandings(): List<DriverStandings> =
        withContext(Dispatchers.IO) {
            apiService.getCurrentSeasonDriverStandings().mRDataDriver.standingsTable.standingsLists[0].driverStandings.toDomainDriverStandings()
        }

    override suspend fun getCurrentSeasonConstructorStandings(): List<ConstructorStandings> =
        withContext(Dispatchers.IO) {
            apiService.getCurrentSeasonConstructorStandings().mRDataDriver.standingsTable.standingsLists[0].constructorStandings.toDomainConstructorStandings()
        }

    override suspend fun getCurrentSeasonRaces(): List<GrandPrix> = withContext(Dispatchers.IO) {
        apiService.getCurrentSeasonRaces().mrData.raceTable.networkGrandPrixes.toDomainGrandPrixList()
    }

    override suspend fun getLastRace(): GrandPrix = withContext(Dispatchers.IO) {
        apiService.getLastRace().mrData.raceTable.networkGrandPrixes[0].toDomainGrandPrix()
    }

    override suspend fun getRace(season: String, round: String): GrandPrix =
        withContext(Dispatchers.IO) {
            apiService.getRace(season, round).mrData.raceTable.networkGrandPrixes[0].toDomainGrandPrix()
        }
}