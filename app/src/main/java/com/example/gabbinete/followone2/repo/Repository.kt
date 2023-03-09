package com.example.gabbinete.followone2.repo

import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix

interface Repository {
//    suspend fun getCurrentDrivers(updateData: Boolean): List<LocalDriver>
    suspend fun getCurrentSeasonDriverStandings(updateData: Boolean): List<LocalDriverStandings>
    suspend fun getCurrentSeasonConstructorStandings(updateData: Boolean): List<LocalConstructorStandings>
    suspend fun getCurrentSeasonRaces(updateData: Boolean): List<LocalGrandPrix>
    suspend fun getLastRace(updateData: Boolean): LocalGrandPrix
    suspend fun getRace(round: String): LocalGrandPrix
}