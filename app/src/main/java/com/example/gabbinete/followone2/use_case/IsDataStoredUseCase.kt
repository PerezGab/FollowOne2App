package com.example.gabbinete.followone2.use_case

import com.example.gabbinete.followone2.repo.Repository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class IsDataStoredUseCase @Inject constructor(val repo: Repository): IsConditionUseCase<Boolean> {

    override operator fun invoke(): StateFlow<Boolean> = repo.isDataStored
}