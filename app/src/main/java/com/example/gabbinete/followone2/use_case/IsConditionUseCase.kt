package com.example.gabbinete.followone2.use_case

import kotlinx.coroutines.flow.StateFlow

interface IsConditionUseCase<T> {
    operator fun invoke(): StateFlow<T>
}