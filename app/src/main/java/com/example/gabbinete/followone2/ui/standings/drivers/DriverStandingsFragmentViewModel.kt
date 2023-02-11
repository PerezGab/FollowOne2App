package com.example.gabbinete.followone2.ui.standings.drivers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.entities.DriverStandings
import com.example.gabbinete.followone2.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverStandingsFragmentViewModel @Inject constructor(private val repo: Repository): ViewModel() {

    private val _progressStatus = MutableStateFlow<Boolean>(false)

    private val _driverStandings = MutableStateFlow<List<DriverStandings>?>(null)
    val driverStandings = _driverStandings.asStateFlow()

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


}