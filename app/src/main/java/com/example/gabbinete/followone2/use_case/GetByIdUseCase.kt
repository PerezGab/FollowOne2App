package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.util.Response
import kotlinx.coroutines.flow.Flow

interface GetByIdUseCase<T> {
    suspend operator fun invoke(argument: String): Flow<Response<T>>
}