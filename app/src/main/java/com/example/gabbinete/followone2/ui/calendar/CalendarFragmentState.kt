package com.example.gabbinete.followone2.ui.calendar

import com.example.gabbinete.followone2.domain.GrandPrix

data class CalendarFragmentState(
    val calendar: List<GrandPrix>?,
    val isLoading: Boolean
)