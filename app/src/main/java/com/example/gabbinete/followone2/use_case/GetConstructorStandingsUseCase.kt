package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.repo.Repository
import com.example.gabbinete.followone2.util.toDomainConstructorStandings
import javax.inject.Inject

class GetConstructorStandingsUseCase @Inject constructor(private val repo: Repository) :
    UseCase<ConstructorStandings> {
    override suspend operator fun invoke(updateData: Boolean): List<ConstructorStandings> {
        return repo.getCurrentSeasonConstructorStandings(updateData).toDomainConstructorStandings()
    }
}