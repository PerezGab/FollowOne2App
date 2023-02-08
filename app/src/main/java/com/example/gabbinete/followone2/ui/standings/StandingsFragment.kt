package com.example.gabbinete.followone2.ui.standings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gabbinete.followone2.databinding.FragmentStandingsBinding
import com.example.gabbinete.followone2.entities.SeasonStandings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StandingsFragment : Fragment() {

    private val viewModel: StandingsFragmentViewModel by viewModels()

    lateinit var binding: FragmentStandingsBinding
    lateinit var adapter: StandingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStandingsBinding.inflate(inflater, container, false)
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

        lifecycleScope.launch {
            viewModel.constructorStandings.collect {
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
        data: List<SeasonStandings>
    ) {
        adapter.addStandings(data[0].standings)
        binding.apply {
            "Season ${data[0].season}".also { seasonText.text = it }
            "Round ${data[0].round}".also { roundsText.text = it }
        }

        binding.apply {
            standingsList.apply {
                adapter = this@StandingsFragment.adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}