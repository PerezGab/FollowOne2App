package com.example.gabbinete.followone2.ui.driver_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gabbinete.followone2.domain.Driver
import com.example.gabbinete.followone2.use_case.GetByIdUseCase
import com.example.gabbinete.followone2.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverProfileViewModel @Inject constructor(
    private val getDriverByIdUseCase: GetByIdUseCase<Driver>
) : ViewModel() {

    private val _state = MutableStateFlow(DriverProfileState(null, true))
    val state = _state.asStateFlow()

    fun getDriverById(driverId: String) {
        viewModelScope.launch {
            getDriverByIdUseCase(driverId).collect { result ->
                when (result) {
                    is Response.Loading -> _state.update {
                        it.copy(driver = result.data, isLoading = result.isLoading)
                    }

                    is Response.Error -> _state.update {
                        it.copy(driver = result.data, isLoading = result.isLoading)
                    }

                    is Response.Success -> _state.update {
                        it.copy(driver = result.data, isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}