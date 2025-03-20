package com.example.gabbinete.followone2.ui.standings.constructor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gabbinete.followone2.databinding.FragmentConstructorStandingsBinding
import com.example.gabbinete.followone2.domain.ConstructorStandings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConstructorStandingsFragment : Fragment() {

    private var _binding: FragmentConstructorStandingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ConstructorStandingsAdapter
    private val viewModel: ConstructorStandingsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentConstructorStandingsBinding.inflate(inflater, container, false)
        adapter = ConstructorStandingsAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    binding.constructorStandingsProgressBar.visibility = if (state.isLoading) View.VISIBLE else View.INVISIBLE
                    binding.recyclerView.visibility = if (state.isLoading) View.INVISIBLE else View.VISIBLE

                    state.constructorStandings?.let {
                        setupStandingViews(it)
                    }
                }
        }
    }

    private fun setupStandingViews(list: List<ConstructorStandings>) {
        adapter.addStandings(list)

        binding.apply {
            recyclerView.apply {
                adapter = this@ConstructorStandingsFragment.adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}