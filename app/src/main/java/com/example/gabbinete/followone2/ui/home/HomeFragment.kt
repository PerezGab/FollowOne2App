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
            viewModel.lastGP.collect { binding.lastGpName.text = it?.raceName }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.nextGP.collect {
                it?.let {
                    binding.nextGpName.text = it.raceName
                    binding.nextGpDate.text = it.date
                    binding.nextGpCircuitName.text = it.circuit?.circuitName
                    binding.nextRaceRound.text = if (it.round == null) "" else "Round " + it.round
                }
            }
        }

//        val localTime = LocalDateTime.now()
//        binding.textView.text = localTime.toString()


        return binding.root
    }
}