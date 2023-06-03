package com.example.gabbinete.followone2.ui.grand_prix_profile.results

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gabbinete.followone2.R
import com.example.gabbinete.followone2.databinding.FragmentGrandPrixResultsBinding
import com.example.gabbinete.followone2.ui.grand_prix_profile.GrandPrixProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "GrandPrixResultsFrag"

@AndroidEntryPoint
class GrandPrixResultsFragment : Fragment() {

    private var _binding: FragmentGrandPrixResultsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GrandPrixProfileViewModel by navGraphViewModels(R.id.grandPrixProfileFragment) { defaultViewModelProviderFactory }
    private lateinit var adapter: GrandPrixResultsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGrandPrixResultsBinding.inflate(inflater, container, false)
        adapter = GrandPrixResultsAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated is called.")
        binding.recyclerView.apply {
            adapter = this@GrandPrixResultsFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect { state ->
                state.grandPrix.apply {
                    Log.d(TAG, "GrandPrix is ${this?.raceName}")

                    this?.raceResults?.let {
                        adapter.setupRaceResults(it)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}