package com.example.gabbinete.followone2.ui.standings.constructor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.domain.ConstructorStandings
import com.example.gabbinete.followone2.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConstructorStandingsViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    private val _progressStatus = MutableStateFlow(false)

    private val _constructorStandings = MutableStateFlow<List<ConstructorStandings>?>(null)
    val constructorStandings = _constructorStandings.asStateFlow()

    init {
        getConstructorStandings()
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
}