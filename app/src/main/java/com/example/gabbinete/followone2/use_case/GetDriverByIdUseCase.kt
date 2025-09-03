package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.domain.Driver
import com.example.gabbinete.followone2.repo.Repository
import com.example.gabbinete.followone2.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetDriverByIdUseCase @Inject constructor(private val repo: Repository) :
    GetByIdUseCase<Driver> {
    override suspend fun invoke(argument: String): Flow<Response<Driver>> {

        return repo.getDriverById(argument)
    }
}