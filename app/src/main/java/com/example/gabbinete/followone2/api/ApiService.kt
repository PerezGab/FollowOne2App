package com.example.gabbinete.followone2.api

import com.example.gabbinete.followone2.api.models.ConstructorStandingsResponse
import com.example.gabbinete.followone2.api.models.DriverResponse
import com.example.gabbinete.followone2.api.models.DriverStandingsResponse
import com.example.gabbinete.followone2.api.models.RaceResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // List of Drivers from current season
    @GET("current/drivers")
    suspend fun getCurrentDrivers(): DriverResponse

    // Driver Standings of current season
    @GET("current/driverStandings")
    suspend fun getCurrentSeasonDriverStandings(): DriverStandingsResponse

    // Constructor Standings of current season
    @GET("current/constructorStandings")
    suspend fun getCurrentSeasonConstructorStandings(): ConstructorStandingsResponse

    // Main of Season Races (only includes name, circuit, location and dates).
    @GET("current")
    suspend fun getCurrentSeasonRaces(): RaceResponse

    // Main of Last Grand Prix + Race Results
    @GET("current/last/results")
    suspend fun getLastRace(): RaceResponse

    // Main of Last Grand Prix + Qualifying Results
    @GET("current/last/qualifying")
    suspend fun getLastQualy(): RaceResponse

    // Main of Grand Prix + Practice, Qualifying and Sprint sessions date.
    @GET("current/{round}")
    suspend fun getRace(@Path("round") round: String): RaceResponse

    // Main of Grand Prix + Race Results
    @GET("current/{round}/results")
    suspend fun getRaceResults(@Path("round") round: String): RaceResponse

    // Main of Grand Prix + Qualifying Results
    @GET("current/{round}/qualifying")
    suspend fun getQualyResults(@Path("round") round: String): RaceResponse
}