package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.util.Response
import kotlinx.coroutines.flow.Flow

interface GetTablesUseCase<T> {
    operator fun invoke(updateData: Boolean): Flow<Response<List<T>>>
}