package com.example.gabbinete.followone2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gabbinete.followone2.database.entities.*

@Dao
interface F1Dao {

//    @Query("SELECT * FROM drivers")
//    suspend fun getDrivers(): List<LocalDriver>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertDrivers(localDrivers: List<LocalDriver>)

    @Query("SELECT * FROM driver_standings")
    suspend fun getDriverStandings(): List<LocalDriverStandings>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDriverStandings(standings: List<LocalDriverStandings>)

    @Query("SELECT * FROM constructor_standings")
    suspend fun getConstructorStandings(): List<LocalConstructorStandings>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConstructorStandings(standings: List<LocalConstructorStandings>)

    @Query("SELECT * FROM grand_prix")
    suspend fun getSeasonRaces(): List<LocalGrandPrix>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeasonRaces(races: List<LocalGrandPrix>)

    @Query("DELETE FROM grand_prix")
    suspend fun clearSeasonRaces()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLastRace(race: LocalLastRace)

    @Query("SELECT * FROM last_race")
    suspend fun getLastRace(): LocalLastRace



//    @Query("SELECT * FROM last_race")
//    suspend fun getLastRace(): LocalGrandPrix
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertLastRace(race: LocalGrandPrix)
}