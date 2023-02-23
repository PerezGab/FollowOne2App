package com.example.gabbinete.followone2.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarFragmentViewModel @Inject constructor(private val repo: Repository): ViewModel() {

    private val _listGp = MutableStateFlow<List<GrandPrix>?>(null)
    val listGp = _listGp.asStateFlow()

    init {
        setCalendar()
    }

    private fun setCalendar() {
        viewModelScope.launch {
            _listGp.value = repo.getCurrentSeasonRaces()
        }
    }
}

