package com.example.gabbinete.followone2.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gabbinete.followone2.api.ApiDataSource
import com.example.gabbinete.followone2.repo.Repository
import com.example.gabbinete.followone2.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCalendarBinding.inflate(inflater, container, false)

//        val viewModel: CalendarFragmentViewModel by viewModels { viewModelFactory }

        return binding.root
    }
}