package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.repo.Repository
import javax.inject.Inject

class GetLastRaceUseCase @Inject constructor(private val repo: Repository) : UseCase<GrandPrix> {

    override suspend fun invoke(updateData: Boolean): List<GrandPrix> {
        return listOf(repo.getLastRace(updateData).toDomainGrandPrix())
    }
}