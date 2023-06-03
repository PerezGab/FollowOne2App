package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.repo.Repository
import com.example.gabbinete.followone2.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRaceByRoundUseCase @Inject constructor(private val repo: Repository) :
    GetByIdUseCase<GrandPrix> {
    override suspend fun invoke(argument: String): Flow<Response<GrandPrix>> {

        repo.updateRaceResultsByRound(argument)
        return repo.getRaceByRound(argument)
    }
}