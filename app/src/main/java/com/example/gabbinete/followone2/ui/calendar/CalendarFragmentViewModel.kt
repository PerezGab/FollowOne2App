package com.example.gabbinete.followone2.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CalendarFragmentViewModel @Inject constructor(
    @Named("getSeasonRaces") private val getSeasonRacesUseCase: UseCase<GrandPrix>
    ) : ViewModel() {

    private val _listGp = MutableStateFlow<List<GrandPrix>?>(null)
    val listGp = _listGp.asStateFlow()

    init {
        setCalendar()
    }

    private fun setCalendar() {
        viewModelScope.launch {
            _listGp.value = getSeasonRacesUseCase(false)
        }
    }
}

