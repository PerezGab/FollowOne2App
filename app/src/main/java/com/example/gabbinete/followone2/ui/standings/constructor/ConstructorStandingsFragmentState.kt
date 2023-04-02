package com.example.gabbinete.followone2.ui.standings.constructor

import com.example.gabbinete.followone2.domain.ConstructorStandings

data class ConstructorStandingsFragmentState(
    val constructorStandings: List<ConstructorStandings>?,
    val isLoading: Boolean
)
