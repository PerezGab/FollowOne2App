package com.example.gabbinete.followone2.ui.home

import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.domain.RaceResults

data class HomeFragmentState(
    val lastGP: GrandPrix?,
    val nextGP: GrandPrix?,
    val isLoading: Boolean,
    val nextGpCountdown: String,
    val lastGpFastestLap: RaceResults?,
    val nextGpDate: String,
    val shouldUpdateCountdown: Boolean
)