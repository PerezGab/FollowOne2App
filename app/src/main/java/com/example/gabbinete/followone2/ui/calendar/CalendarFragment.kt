package com.example.gabbinete.followone2.ui.calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gabbinete.followone2.databinding.FragmentCalendarBinding
import com.example.gabbinete.followone2.domain.GrandPrix
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalendarFragment : Fragment() {

    private val viewModel: CalendarFragmentViewModel by viewModels()

    lateinit var adapter: CalendarAdapter
    lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        adapter = CalendarAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                state.calendar?.let { setupCalendar(it) }
            }
        }

        Log.d(
            "CalendarFragment",
            "isAttachedToWindow is ${binding.grandPrixList.isAttachedToWindow}"
        )
    }

    private fun setupCalendar(list: List<GrandPrix>) {
        adapter.setupAdapter(list)

        binding.apply {
            grandPrixList.apply {
                adapter = this@CalendarFragment.adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}