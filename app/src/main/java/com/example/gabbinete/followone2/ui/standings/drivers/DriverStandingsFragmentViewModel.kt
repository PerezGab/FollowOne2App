package com.example.gabbinete.followone2.ui.standings.drivers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverStandingsFragmentViewModel @Inject constructor(
    private val getDriverStandingsUseCase: UseCase<DriverStandings>
    ) : ViewModel() {

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
                _driverStandings.value = getDriverStandingsUseCase(true)
            } catch (e: Exception) {
                _driverStandings.value = null
            }
            _progressStatus.value = false
        }
    }


}