package com.example.gabbinete.followone2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gabbinete.followone2.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)


        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                binding.lastGpName.text = it.lastGP?.raceName

                it.nextGP?.let { nextGP ->
                    binding.nextGpName.text = nextGP.raceName
                    binding.nextGpDate.text = nextGP.date
                    binding.nextGpCircuitName.text = nextGP.circuit?.circuitName
                    binding.nextRaceRound.text = "Round " + nextGP.round
                }
            }
        }

        return binding.root
    }
}