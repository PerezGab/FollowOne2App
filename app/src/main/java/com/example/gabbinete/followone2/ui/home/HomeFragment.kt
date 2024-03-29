package com.example.gabbinete.followone2.ui.home

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
import com.example.gabbinete.followone2.R
import com.example.gabbinete.followone2.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeFragmentViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)



        lifecycleScope.launch {

            viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    binding.apply {
                        state.lastGP?.let { lastGP ->
                            lastGpName.text = lastGP.raceName
                            lastGP.raceResults?.let { raceResults ->
                                viewModel.lastGpFastestLap(raceResults)
                                lastGpFirstDriver.text = raceResults[0].driver.familyName
                                lastGpSecondDriver.text = raceResults[1].driver.familyName
                                lastGpThirdDriver.text = raceResults[2].driver.familyName
                            }
                            lastGP.qualifyingResults?.let { qualyResults ->
                                lastGpPoleDriver.text = qualyResults[0].networkDriver.familyName
                                lastGpPoleTime.text = qualyResults[0].q3
                            }
                        }

                        state.lastGpFastestLap?.let { fastestLap ->
                            lastGpFastestLapDriver.text = fastestLap.driver.familyName
                            lastGpFastestLapTime.text = fastestLap.fastestLap?.time?.time
                        }

                        state.nextGP?.let { nextGP ->
                            if (state.shouldUpdateCountdown) {
                                Log.d(TAG, "shouldUpdateCountdown value is ${state.shouldUpdateCountdown}")
                                viewModel.calculateCountdown(nextGP.date!!, nextGP.time!!)
                                viewModel.timerCountdown()
                            }
                            Log.d(TAG, "shouldUpdateCountdown value is ${state.shouldUpdateCountdown}")
                            nextGP.date?.let { viewModel.formatDate(it) }
                            nextGpName.text = nextGP.raceName
                            nextGpCircuitName.text = nextGP.circuit?.circuitName
                            nextRaceRound.text = getString(R.string.round_variable, nextGP.round)
                        }

                        nextGpDate.text = state.nextGpDate
                        nextGpCountdownTime.text = state.nextGpCountdown
                    }
                }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}