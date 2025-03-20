package com.example.gabbinete.followone2.ui.grand_prix_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.use_case.GetByIdUseCase
import com.example.gabbinete.followone2.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GrandPrixProfileViewModel @Inject constructor(
    private val getRaceResultsByRoundUseCase: GetByIdUseCase<GrandPrix>
) :
    ViewModel() {

    private val _state = MutableStateFlow(GrandPrixProfileState(null, true))
    val state = _state.asStateFlow()

    fun getRaceResultsByRound(round: String) {
        viewModelScope.launch {
            getRaceResultsByRoundUseCase(round).collect { result ->
                when (result) {
                    is Response.Loading -> _state.update { state ->
                        state.copy(
                            grandPrix = result.data,
                            isLoading = result.isLoading
                        )
                    }

                    is Response.Success -> _state.update { state ->
                        state.copy(
                            grandPrix = result.data,
                            isLoading = result.isLoading
                        )
                    }

                    is Response.Error -> _state.update { state ->
                        state.copy(
                            grandPrix = result.data,
                            isLoading = result.isLoading
                        )
                    }
                }
            }
        }
    }
}