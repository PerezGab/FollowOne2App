package com.example.gabbinete.followone2.database

import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.repo.LocalDataSource
import com.example.gabbinete.followone2.util.toDomainConstructorStandings
import com.example.gabbinete.followone2.util.toDomainDriverStandings
import com.example.gabbinete.followone2.util.toDomainGrandPrix
import com.example.gabbinete.followone2.util.toLocalGrandPrixList
import javax.inject.Inject

class RoomDataSource @Inject constructor(private val room: F1Dao) : LocalDataSource {

    override suspend fun getDriversStandings(): List<DriverStandings> =
        room.getDriverStandings().toDomainDriverStandings()

    override suspend fun getConstructorStandings(): List<ConstructorStandings> =
        room.getConstructorStandings().toDomainConstructorStandings()

    override suspend fun getSeasonRaces(): List<GrandPrix> =
        room.getSeasonRaces().toDomainGrandPrix()

    override suspend fun insertSeasonRaces(races: List<GrandPrix>) =
        room.insertSeasonRaces(races.toLocalGrandPrixList())

    //    override suspend fun getLastRace(): GrandPrix = TODO()
//        room.getLastRace().toDomainGrandPrix()
}