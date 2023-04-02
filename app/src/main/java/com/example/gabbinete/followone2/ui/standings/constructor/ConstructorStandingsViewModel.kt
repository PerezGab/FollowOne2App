package com.example.gabbinete.followone2.ui.standings.constructor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.use_case.GetTablesUseCase
import com.example.gabbinete.followone2.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConstructorStandingsViewModel @Inject constructor(
    private val getConstructorStandingsUseCase: GetTablesUseCase<ConstructorStandings>
) : ViewModel() {

    private val _state = MutableStateFlow(ConstructorStandingsFragmentState(null, true))
    val state = _state.asStateFlow()

    init {
        getConstructorStandings()
    }

    private fun getConstructorStandings() {
        viewModelScope.launch {
            getConstructorStandingsUseCase(false).collect { result ->
                when (result) {
                    is Response.Loading -> _state.update { it.copy(isLoading = result.isLoading) }

                    is Response.Success -> result.data?.let { standings ->
                        _state.update { state ->
                            state.copy(
                                constructorStandings = standings,
                                isLoading = result.isLoading
                            )
                        }
                    }

                    is Response.Error -> result.data?.let { standings ->
                        _state.update { state ->
                            state.copy(
                                constructorStandings = standings,
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