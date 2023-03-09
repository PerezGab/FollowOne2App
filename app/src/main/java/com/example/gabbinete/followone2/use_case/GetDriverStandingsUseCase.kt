package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.repo.Repository
import com.example.gabbinete.followone2.util.toDomainDriverStandings
import javax.inject.Inject

class GetDriverStandingsUseCase @Inject constructor(private val repo: Repository) :
    UseCase<DriverStandings> {
    override suspend fun invoke(updateData: Boolean): List<DriverStandings> {
        return repo.getCurrentSeasonDriverStandings(updateData).toDomainDriverStandings()
    }
}