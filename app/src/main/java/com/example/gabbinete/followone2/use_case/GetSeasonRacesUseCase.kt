package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.repo.Repository
import com.example.gabbinete.followone2.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSeasonRacesUseCase @Inject constructor(private val repo: Repository) :
    GetTablesUseCase<GrandPrix> {

    override operator fun invoke(updateData: Boolean): Flow<Response<List<GrandPrix>>> {
        return repo.getCurrentSeasonRaces(updateData)
    }
}