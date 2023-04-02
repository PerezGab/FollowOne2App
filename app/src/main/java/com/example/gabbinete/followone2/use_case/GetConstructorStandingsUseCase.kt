package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.repo.Repository
import com.example.gabbinete.followone2.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConstructorStandingsUseCase @Inject constructor(private val repo: Repository) :
    GetTablesUseCase<ConstructorStandings> {
    override operator fun invoke(updateData: Boolean): Flow<Response<List<ConstructorStandings>>> {
        return repo.getCurrentSeasonConstructorStandings(updateData)
    }
}