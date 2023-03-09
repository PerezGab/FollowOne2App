package com.example.gabbinete.followone2.use_case

interface UseCase<T> {
    suspend operator fun invoke(updateData: Boolean): List<T>
}