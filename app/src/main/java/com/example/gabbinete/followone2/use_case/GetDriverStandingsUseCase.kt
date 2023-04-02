package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.repo.Repository
import com.example.gabbinete.followone2.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDriverStandingsUseCase @Inject constructor(private val repo: Repository) :
    GetTablesUseCase<DriverStandings> {
    override operator fun invoke(updateData: Boolean): Flow<Response<List<DriverStandings>>> {
        return repo.getCurrentSeasonDriverStandings(updateData)
    }
}