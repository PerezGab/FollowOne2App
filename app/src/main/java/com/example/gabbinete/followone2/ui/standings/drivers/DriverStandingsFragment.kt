package com.example.gabbinete.followone2.ui.standings.drivers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gabbinete.followone2.databinding.FragmentDriverStandingsBinding
import com.example.gabbinete.followone2.domain.DriverStandings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DriverStandingsFragment : Fragment() {

    private val viewModel: DriverStandingsFragmentViewModel by viewModels()

    private lateinit var binding: FragmentDriverStandingsBinding
    private lateinit var adapter: StandingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDriverStandingsBinding.inflate(inflater, container, false)
        adapter = StandingsAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.driverStandings.collect {
                it?.let {
                    setupStandingViews(it)
                }
            }
        }

        Log.d(
            "StandingsFragments",
            "isAttachedToWindow is ${binding.standingsList.isAttachedToWindow}"
        )
    }

    private fun setupStandingViews(
        data: List<DriverStandings>
    ) {
        adapter.addStandings(data)

        binding.apply {
            standingsList.apply {
                adapter = this@DriverStandingsFragment.adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}