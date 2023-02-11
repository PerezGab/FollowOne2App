package com.example.gabbinete.followone2.ui.standings.constructor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gabbinete.followone2.databinding.FragmentConstructorStandingsBinding
import com.example.gabbinete.followone2.entities.ConstructorStandings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConstructorStandingsFragment : Fragment() {

    private lateinit var binding: FragmentConstructorStandingsBinding
    private lateinit var adapter: ConstructorStandingsAdapter
    private val viewModel: ConstructorStandingsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentConstructorStandingsBinding.inflate(inflater, container, false)
        adapter = ConstructorStandingsAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.constructorStandings.collect {
                it?.let {
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
}