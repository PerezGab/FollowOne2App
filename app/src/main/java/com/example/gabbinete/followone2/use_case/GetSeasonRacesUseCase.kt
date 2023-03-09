package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.repo.Repository
import com.example.gabbinete.followone2.util.toDomainGrandPrix
import javax.inject.Inject

class GetSeasonRacesUseCase @Inject constructor(private val repo: Repository) : UseCase<GrandPrix> {

    override suspend fun invoke(updateData: Boolean): List<GrandPrix> {
        return repo.getCurrentSeasonRaces(updateData).toDomainGrandPrix()
    }
}