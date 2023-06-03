package com.example.gabbinete.followone2.use_case

import android.util.Log
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.repo.Repository
import com.example.gabbinete.followone2.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
private const val TAG = "GetSeasonRacesUseCase"
class GetSeasonRacesUseCase @Inject constructor(private val repo: Repository) :
    GetTablesUseCase<GrandPrix> {

    override operator fun invoke(updateData: Boolean): Flow<Response<List<GrandPrix>>> {
        Log.d(TAG, "updateData is $updateData")
        return repo.getCurrentSeasonRaces(updateData)
    }
}