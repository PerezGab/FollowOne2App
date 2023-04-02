package com.example.gabbinete.followone2.util

sealed class Response<T>(val data: T? = null, val message: String? = null, val isLoading: Boolean) {
    class Success<T>(data: T, isLoading: Boolean = false): Response<T>(data, isLoading = isLoading)
    class Error<T>(data: T?, message: String, isLoading: Boolean = false): Response<T>(data, message, isLoading)
    class Loading<T>(isLoading: Boolean = true): Response<T>(isLoading = isLoading)
}