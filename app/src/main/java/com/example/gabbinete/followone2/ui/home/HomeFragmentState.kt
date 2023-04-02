package com.example.gabbinete.followone2.ui.home

import com.example.gabbinete.followone2.domain.GrandPrix

data class HomeFragmentState(
    val lastGP: GrandPrix?,
    val nextGP: GrandPrix?,
    val isLoading: Boolean
)