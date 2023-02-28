package com.example.gabbinete.followone2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val CURRENT_SEASON = "current"

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    private val _lastGP = MutableStateFlow<GrandPrix?>(null)
    val lastGP = _lastGP.asStateFlow()

    private val _nextGP = MutableStateFlow<GrandPrix?>(null)
    val nextGP = _nextGP.asStateFlow()

    init {
        setNextRace()
        setLastRace()
    }

    private fun setLastRace() {
        viewModelScope.launch {
//            _lastGP.value = (repo.getCurrentSeasonDriverStandings()[0].standings[0] as DriverStandings).driver.toDomainDriver()
            _lastGP.value = repo.getLastRace()
        }
    }

    private fun setNextRace() {
        viewModelScope.launch {
            val nextGpItem = repo.getLastRace().round?.let { it.toInt() + 1 }
            val totalRounds = repo.getCurrentSeasonRaces().count()
            if (nextGpItem != null) {
                if (nextGpItem >= totalRounds) {
                    val endOfSeason = GrandPrix.postSeason()
                    _nextGP.value = endOfSeason
                } else {
                    _nextGP.value = repo.getRace(CURRENT_SEASON, nextGpItem.toString())
                }
            }
        }
    }
}

