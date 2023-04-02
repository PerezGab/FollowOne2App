package com.example.gabbinete.followone2.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.use_case.GetTablesUseCase
import com.example.gabbinete.followone2.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CalendarFragmentViewModel @Inject constructor(
    @Named("getSeasonRaces") private val getSeasonRacesUseCase: GetTablesUseCase<GrandPrix>
) : ViewModel() {

    private val _state = MutableStateFlow(CalendarFragmentState(null, true))
    val state = _state.asStateFlow()

    init {
        setCalendar()
    }

    private fun setCalendar() {
        viewModelScope.launch {
            getSeasonRacesUseCase(false).collect { result ->
                when (result) {
                    is Response.Loading -> _state.update { state -> state.copy(isLoading = state.isLoading) }

                    is Response.Success -> result.data?.let { calendar ->
                        _state.update { state ->
                            state.copy(calendar = calendar, isLoading = result.isLoading)
                        }
                    }

                    is Response.Error -> result.data?.let { calendar ->
                        _state.update { state ->
                            state.copy(calendar = calendar, isLoading = result.isLoading)
                        }
                    }
//                    result.message TODO()
                }

            }
        }
    }
}

