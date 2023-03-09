package com.example.gabbinete.followone2.ui.home

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
class HomeFragmentViewModel @Inject constructor(
    @Named("getSeasonRaces") private val getSeasonRacesUseCase: UseCase<GrandPrix>,
    @Named("getLastRace") private val getLastRaceUseCase: UseCase<GrandPrix>
) : ViewModel() {

    private val _lastGP = MutableStateFlow<GrandPrix?>(null)
    val lastGP = _lastGP.asStateFlow()

    private val _nextGP = MutableStateFlow<GrandPrix?>(null)
    val nextGP = _nextGP.asStateFlow()

    init {
        setLastRace()
        setNextRace()
    }

    private fun setLastRace() {
        viewModelScope.launch {
//            _lastGP.value = (repo.getCurrentSeasonDriverStandings()[0].standings[0] as DriverStandings).driver.toDomainDriver()
            _lastGP.value = getLastRaceUseCase(true)[0]
        }
    }

    private fun setNextRace() {
        viewModelScope.launch {
            val nextGpItem = getLastRaceUseCase(false)[0].round?.let { it.toInt() + 1 }
            val totalRounds = getSeasonRacesUseCase(true).count()
            if (nextGpItem != null) {
                if (nextGpItem >= totalRounds) {
                    val endOfSeason = GrandPrix.postSeason()
                    _nextGP.value = endOfSeason
                } else {
                    _nextGP.value = getSeasonRacesUseCase(false)[nextGpItem]
                }
            }
        }
    }
}

