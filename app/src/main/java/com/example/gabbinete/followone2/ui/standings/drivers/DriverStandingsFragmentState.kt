package com.example.gabbinete.followone2.ui.standings.drivers

import com.example.gabbinete.followone2.domain.DriverStandings

data class DriverStandingsFragmentState(
    val driverStandings: List<DriverStandings>?,
    val isLoading: Boolean
)
