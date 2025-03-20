package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.repo.Repository
import javax.inject.Inject

class OnStartUpUseCase @Inject constructor(private val repo: Repository): OnEventUseCase {
    override suspend fun invoke() {
        repo.updateSeasonRaces()
        repo.updateLastRace()
        repo.updateDriverStandings()
        repo.updateConstructorStandings()
    }
}