package com.example.gabbinete.followone2.ui.grand_prix_profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gabbinete.followone2.R
import com.example.gabbinete.followone2.databinding.FragmentGrandPrixProfileBinding
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.ui.grand_prix_profile.circuit.GrandPrixCircuitFragment
import com.example.gabbinete.followone2.ui.grand_prix_profile.results.GrandPrixResultsFragment
import com.example.gabbinete.followone2.ui.grand_prix_profile.schedule.GrandPrixScheduleFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "GrandPrixProfileFrag"
private var gp: GrandPrix? = null

@AndroidEntryPoint
class GrandPrixProfileFragment : Fragment() {

    private var _binding: FragmentGrandPrixProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: GrandPrixProfileAdapter

    private val argument: GrandPrixProfileFragmentArgs by navArgs()
    private val viewModel: GrandPrixProfileViewModel by navGraphViewModels(R.id.grandPrixProfileFragment) { defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGrandPrixProfileBinding.inflate(inflater, container, false)

        viewModel.getRaceResultsByRound(argument.grandPrixRound)

        adapter = GrandPrixProfileAdapter(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    it.grandPrix?.let { grandPrix ->
                        Log.d(TAG, "GrandPrix is ${it.grandPrix.raceName}")
                        Log.d(TAG, "Race results are: ${it.grandPrix.raceResults}")
                        gp = grandPrix
                        setupTabs(grandPrix)
                    }
                }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupTabs(gp: GrandPrix) {
        binding.apply {
            grandPrixViewPager.adapter = adapter
            TabLayoutMediator(grandPrixTabLayout, grandPrixViewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = if (gp.raceResults.isNullOrEmpty()) "Schedule" else "Results"
                    1 -> tab.text = "Circuit"
                }
            }.attach()
        }
    }
}

class GrandPrixProfileAdapter(fragment: GrandPrixProfileFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        Log.d(TAG, "GrandPrixProfileAdapter calls createFragment()")
        return when (position) {
            0 -> if (gp?.raceResults.isNullOrEmpty()) GrandPrixScheduleFragment() else GrandPrixResultsFragment()
            else -> GrandPrixCircuitFragment()
        }
    }
}