package com.example.gabbinete.followone2.database

import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix
import com.example.gabbinete.followone2.repo.LocalDataSource
import javax.inject.Inject

class RoomDataSource @Inject constructor(private val room: F1Dao) : LocalDataSource {

    override suspend fun getDriversStandings(): List<LocalDriverStandings> =
        room.getDriverStandings()

    override suspend fun getConstructorStandings(): List<LocalConstructorStandings> =
        room.getConstructorStandings()

    override suspend fun getSeasonRaces(): List<LocalGrandPrix> =
        room.getSeasonRaces()

    override suspend fun insertSeasonRaces(races: List<LocalGrandPrix>) =
        room.insertSeasonRaces(races)

    //    override suspend fun getLastRace(): GrandPrix = TODO()
//        room.getLastRace().toDomainGrandPrix()
}