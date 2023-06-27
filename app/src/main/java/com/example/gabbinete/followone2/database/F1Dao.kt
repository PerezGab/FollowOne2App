package com.example.gabbinete.followone2.database

import androidx.room.*
import com.example.gabbinete.followone2.api.models.NetworkRaceResult
import com.example.gabbinete.followone2.api.models.QualifyingResult
import com.example.gabbinete.followone2.database.entities.*

@Dao
interface F1Dao {

    @Query("SELECT * FROM driver_standings")
    fun getDriverStandings(): List<LocalDriverStandings>

    @Upsert
    suspend fun upsertDriverStandings(standings: List<LocalDriverStandings>)

    @Query("SELECT * FROM constructor_standings")
    fun getConstructorStandings(): List<LocalConstructorStandings>

    @Upsert
    suspend fun upsertConstructorStandings(standings: List<LocalConstructorStandings>)

    @Query("SELECT * FROM grand_prix")
    fun getSeasonRaces(): List<LocalGrandPrix>

    @Upsert
    suspend fun upsertSeasonRaces(races: List<LocalGrandPrix>)

    @Query("DELETE FROM grand_prix")
    suspend fun clearSeasonRaces()

    @Upsert
    suspend fun upsertLastRace(race: LocalLastRace)

    @Query("SELECT * FROM last_race")
    fun getLastRace(): List<LocalLastRace>

    @Query("SELECT * FROM grand_prix WHERE round = :round")
    fun getRaceByRound(round: String): LocalGrandPrix

    @Query("UPDATE grand_prix SET raceResults = :raceResults WHERE round = :round")
    fun updateRaceResultWithRound(round: String, raceResults: List<NetworkRaceResult>?)

    @Query("UPDATE grand_prix SET qualifyingResults = :qualyResult WHERE round = :round")
    fun updateQualyResultWithRound(round: String, qualyResult: List<QualifyingResult>?)
}