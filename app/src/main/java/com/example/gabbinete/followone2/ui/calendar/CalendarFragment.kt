package com.example.gabbinete.followone2.ui.calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gabbinete.followone2.databinding.FragmentCalendarBinding
import com.example.gabbinete.followone2.domain.GrandPrix
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "CalendarFragment"
@AndroidEntryPoint
class CalendarFragment : Fragment() {

    private val viewModel: CalendarFragmentViewModel by viewModels()

    private lateinit var adapter: CalendarAdapter
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView started")
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        adapter = CalendarAdapter(GrandPrixListener { gp ->
            viewModel.onGrandPrixClick(gp)
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated started")

        lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    state.calendar?.let { setupCalendar(it) }

                    state.navigateToGrandPrixProfile?.let {
                        this@CalendarFragment.findNavController().navigate(
                            CalendarFragmentDirections.actionCalendarFragmentToGrandPrixProfileFragment(it.round, it.raceName)
                        )
                        viewModel.onGrandPrixNavigated()
                    }
                }
        }

        Log.d(
            "CalendarFragment",
            "isAttachedToWindow is ${binding.grandPrixList.isAttachedToWindow}"
        )
    }

    private fun setupCalendar(list: List<GrandPrix>) {
        Log.d(TAG, "setupCalendar is called")
        adapter.setupAdapter(list)

        binding.apply {
            grandPrixList.apply {
                adapter = this@CalendarFragment.adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}