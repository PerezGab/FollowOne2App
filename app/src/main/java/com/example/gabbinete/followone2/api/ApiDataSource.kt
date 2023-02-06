package com.example.gabbinete.followone2.api

import com.example.gabbinete.followone2.entities.Driver
import com.example.gabbinete.followone2.entities.GrandPrix
import com.example.gabbinete.followone2.entities.SeasonStandings
import com.example.gabbinete.followone.repo.*
import com.example.gabbinete.followone2.repo.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class ApiDataSource @Inject constructor(private val apiService: ApiService) : RemoteDataSource {

    override suspend fun getCurrentDrivers(): List<Driver> =
        apiService.getCurrentDrivers().mRData.driverTable.networkDrivers.toDomainDriver()

    override suspend fun getCurrentSeasonDriverStandings(): List<SeasonStandings> =
        apiService.getCurrentSeasonDriverStandings().mRDataDriver.standingsTable.standingsLists.toDomainDriverStandingList()

    override suspend fun getCurrentSeasonConstructorStandings(): List<SeasonStandings> =
        apiService.getCurrentSeasonConstructorStandings().mRDataDriver.standingsTable.standingsLists.toDomainConstructorStandingList()

    override suspend fun getCurrentSeasonRaces(): List<GrandPrix> =
        apiService.getCurrentSeasonRaces().mrData.raceTable.races.toDomainGrandPrixList()

    override suspend fun getLastRace(): GrandPrix =
        apiService.getLastRace().mrData.raceTable.races[0].toDomainGrandPrix()

    override suspend fun getRace(season: String, round: String): GrandPrix =
        apiService.getRace(season, round).mrData.raceTable.races[0].toDomainGrandPrix()
}
