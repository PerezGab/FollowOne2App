package com.example.gabbinete.followone2.database

import android.util.Log
import com.example.gabbinete.followone2.api.models.NetworkRaceResult
import com.example.gabbinete.followone2.api.models.QualifyingResult
import com.example.gabbinete.followone2.database.entities.LocalConstructorStandings
import com.example.gabbinete.followone2.database.entities.LocalDriverStandings
import com.example.gabbinete.followone2.database.entities.LocalGrandPrix
import com.example.gabbinete.followone2.database.entities.LocalLastRace
import com.example.gabbinete.followone2.repo.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "RoomDataSource"

class RoomDataSource @Inject constructor(private val room: F1Dao) : LocalDataSource {

    override suspend fun getDriversStandings(): List<LocalDriverStandings> =
        withContext(Dispatchers.IO) {
            Log.d(TAG, "getDriversStandings sends to Room.")
            room.getDriverStandings()
        }

    override suspend fun upsertDriverStandings(standings: List<LocalDriverStandings>) =
        withContext(Dispatchers.IO) {
            Log.d(TAG, "upsertDriverStandings sends to Room.")
            room.upsertDriverStandings(standings)
        }

    override suspend fun getConstructorStandings(): List<LocalConstructorStandings> =
        withContext(Dispatchers.IO) {
            Log.d(TAG, "getConstructorStandings sends to Room.")
            room.getConstructorStandings()
        }

    override suspend fun upsertConstructorStandings(standings: List<LocalConstructorStandings>) =
        withContext(Dispatchers.IO) {
            Log.d(TAG, "upsertConstructorStandings sends to Room.")
            room.upsertConstructorStandings(standings)
        }

    override suspend fun getSeasonRaces(): List<LocalGrandPrix> = withContext(Dispatchers.IO) {
        Log.d(TAG, "getSeasonRaces sends to Room.")
        room.getSeasonRaces()
    }


    override suspend fun upsertSeasonRaces(races: List<LocalGrandPrix>) =
        withContext(Dispatchers.IO) {
            Log.d(TAG, "upsertSeasonRaces sends to Room.")
            room.upsertSeasonRaces(races)
        }

    override suspend fun clearSeasonRaces() = withContext(Dispatchers.IO) {
        Log.d(TAG, "clearSeasonRaces sends to Room.")
        room.clearSeasonRaces()
    }

    override suspend fun upsertLastRace(race: LocalLastRace) = withContext(Dispatchers.IO) {
        Log.d(TAG, "upsertLastRace sends to Room.")
        room.upsertLastRace(race)
    }

    override suspend fun getLastRace(): List<LocalLastRace> = withContext(Dispatchers.IO) {
        Log.d(TAG, "getLastRace calls Room.")
        room.getLastRace()
    }

    override suspend fun getRaceByRound(round: String): LocalGrandPrix =
        withContext(Dispatchers.IO) {
            Log.d(TAG, "getRaceById calls Room.")
            room.getRaceByRound(round)
        }

    override suspend fun updateRaceResultWithRound(
        round: String,
        raceResults: List<NetworkRaceResult>?
    ) = withContext(Dispatchers.IO) {
        Log.d(TAG, "updateRaceResultWithRound calls Room. Round $round results are being updated.")
        room.updateRaceResultWithRound(round, raceResults)
    }

    override suspend fun updateQualyResultWithRound(
        round: String,
        qualifyingResult: List<QualifyingResult>?
    ) = withContext(Dispatchers.IO) {
        Log.d(TAG, "updateQualyResultWithRound calls Room. Round $round qualies are being updated.")
        room.updateQualyResultWithRound(round, qualifyingResult)
    }
}
