package com.example.gabbinete.followone2.ui.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gabbinete.followone2.api.ApiDataSource
import com.example.gabbinete.followone2.databinding.FragmentStandingsBinding
import com.example.gabbinete.followone2.entities.SeasonStandings
import com.example.gabbinete.followone2.repo.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StandingsFragment : Fragment() {

    private val viewModel: StandingsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStandingsBinding.inflate(inflater, container, false)

        val adapter = StandingsAdapter()

        lifecycleScope.launch {
            viewModel.driverStandings.collect {
                it?.let {
                    setupStandingViews(it, binding, adapter)
//                    viewModel.getDriverStandingsCompleted()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.constructorStandings.collect {
                it?.let {
                    setupStandingViews(it, binding, adapter)
//                    viewModel.getConstructorStandingsCompleted()
                }
            }
        }

        binding.standingsList.adapter = adapter
        return binding.root
    }

    private fun setupStandingViews(
        data: List<SeasonStandings>,
        binding: FragmentStandingsBinding,
        adapter: StandingsAdapter
    ) {
        adapter.addStandings(data[0].standings)
        binding.seasonText.text = "Season ${data[0].season}"
        binding.roundsText.text = "Round ${data[0].round}"
    }

}