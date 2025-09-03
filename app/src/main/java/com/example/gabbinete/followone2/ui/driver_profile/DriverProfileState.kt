package com.example.gabbinete.followone2.ui.driver_profile

import com.example.gabbinete.followone2.domain.Driver

data class DriverProfileState(
    val driver: Driver?,
    val isLoading: Boolean
)
