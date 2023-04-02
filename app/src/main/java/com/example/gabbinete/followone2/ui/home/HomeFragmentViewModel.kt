package com.example.gabbinete.followone2.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.use_case.GetTablesUseCase
import com.example.gabbinete.followone2.use_case.IsConditionUseCase
import com.example.gabbinete.followone2.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "HomeFragmentViewModel"

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    @Named("getSeasonRaces") private val getSeasonRacesUseCase: GetTablesUseCase<GrandPrix>,
    @Named("getLastRace") private val getLastRaceUseCase: GetTablesUseCase<GrandPrix>,
    private val isDataStoredUseCase: IsConditionUseCase<Boolean>
) : ViewModel() {

    private var _state = MutableStateFlow(HomeFragmentState(null, null, true))
    val state = _state.asStateFlow()

    init {
        Log.d(TAG, "init block")
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModelScope.launch {
            isDataStoredUseCase().collect {
                if (it) {
                    setupLastRace()
                    setupNextRace()
                }
            }
        }
    }

    private fun setupLastRace() {
        Log.d(TAG, "setupLastRace is called.")
        viewModelScope.launch {
            getLastRaceUseCase(false).collect { result ->
                when (result) {
                    is Response.Loading -> _state.update { it.copy(isLoading = result.isLoading) }

                    is Response.Success ->
                        result.data?.let { lastGP ->
                            _state.update { state ->
                                state.copy(lastGP = lastGP[0], isLoading = result.isLoading)
                            }
                        }

                    is Response.Error ->
                        result.data?.let { lastGP ->
                            _state.update { state ->
                                state.copy(lastGP = lastGP[0], isLoading = result.isLoading)
                            }
                        }
//                        result.message TODO
                }
            }
        }
    }


    private fun setupNextRace() {
        Log.d(TAG, "setupNextRace is called.")
        viewModelScope.launch {
            getLastRaceUseCase(false).collect { result ->
                result.data?.let { listLastRace ->
                    val nextGpRound = listLastRace[0].round.toInt() + 1
                    getSeasonRacesUseCase(false).collect { seasonRaces ->
                        val totalRounds = seasonRaces.data?.count()
                        when (result) {
                            is Response.Loading -> _state.update { it.copy(isLoading = result.isLoading) }

                            is Response.Success -> {
                                if (totalRounds != null && nextGpRound >= totalRounds) {
                                    _state.update {
                                        it.copy(
                                            nextGP = GrandPrix.postSeason(),
                                            isLoading = result.isLoading
                                        )
                                    }
                                } else {
                                    _state.update {
                                        it.copy(
                                            nextGP = seasonRaces.data?.get(nextGpRound-1),
                                            isLoading = result.isLoading
                                        )
                                    }
                                }
                            }

                            is Response.Error -> {
                                if (totalRounds != null && nextGpRound >= totalRounds) {
                                    _state.update {
                                        it.copy(
                                            nextGP = GrandPrix.postSeason(),
                                            isLoading = result.isLoading
                                        )
                                    }
                                } else {
                                    _state.update {
                                        it.copy(
                                            nextGP = seasonRaces.data?.get(nextGpRound-1),
                                            isLoading = result.isLoading
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

