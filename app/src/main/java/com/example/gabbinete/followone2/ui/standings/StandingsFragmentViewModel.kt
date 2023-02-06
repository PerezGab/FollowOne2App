package com.example.gabbinete.followone2.ui.standings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.entities.SeasonStandings
import com.example.gabbinete.followone2.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandingsFragmentViewModel @Inject constructor(private val repo: Repository): ViewModel() {

    private val _progressStatus = MutableStateFlow<Boolean>(false)

    private val _driverStandings = MutableStateFlow<List<SeasonStandings>?>(null)
    val driverStandings = _driverStandings.asStateFlow()

    private val _constructorStandings = MutableStateFlow<List<SeasonStandings>?>(null)
    val constructorStandings = _constructorStandings.asStateFlow()

    init {
        getDriverStandings()
    }

    private fun getDriverStandings() {
        _progressStatus.value = true
        viewModelScope.launch {
            try {
                _driverStandings.value = repo.getCurrentSeasonDriverStandings()
            } catch (e: Exception) {
                _driverStandings.value = null
            }
            _progressStatus.value = false
        }
    }

    private fun getConstructorStandings() {
        _progressStatus.value = true
        viewModelScope.launch {
            try {
                _constructorStandings.value = repo.getCurrentSeasonConstructorStandings()
            } catch (e: Exception) {
                _constructorStandings.value = null
            }
            _progressStatus.value = false
        }
    }

    fun getDriverStandingsCompleted() {
        _driverStandings.value = null
    }

    fun getConstructorStandingsCompleted() {
        _constructorStandings.value = null
    }
}