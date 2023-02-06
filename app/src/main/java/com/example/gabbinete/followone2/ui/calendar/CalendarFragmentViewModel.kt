package com.example.gabbinete.followone2.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gabbinete.followone2.repo.Repository

class CalendarFragmentViewModel(private val repo: Repository): ViewModel() {

    init {

    }
}

@Suppress("UNCHECKED_CAST")
class CalendarFragmentViewModelFactory(private val repo: Repository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalendarFragmentViewModel::class.java)) {
            return CalendarFragmentViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}