package com.example.gabbinete.followone2.ui.standings.drivers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.use_case.GetTablesUseCase
import com.example.gabbinete.followone2.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverStandingsFragmentViewModel @Inject constructor(
    private val getDriverStandingsUseCase: GetTablesUseCase<DriverStandings>
) : ViewModel() {

    private val _state = MutableStateFlow(DriverStandingsFragmentState(null, true))
    val state = _state.asStateFlow()

    init {
        getDriverStandings()
    }

    private fun getDriverStandings() {
        viewModelScope.launch {
            getDriverStandingsUseCase(false).collect { result ->
                when (result) {
                    is Response.Loading -> _state.update { it.copy(isLoading = result.isLoading) }

                    is Response.Success -> result.data?.let { standings ->
                        _state.update { state ->
                            state.copy(
                                driverStandings = standings,
                                isLoading = result.isLoading
                            )
                        }
                    }

                    is Response.Error -> result.data?.let { standings ->
                        _state.update { state ->
                            state.copy(
                                driverStandings = standings,
                                isLoading = result.isLoading
                            )
                        }
                    }
//                    result.message TODO
                }
            }
        }
    }


}