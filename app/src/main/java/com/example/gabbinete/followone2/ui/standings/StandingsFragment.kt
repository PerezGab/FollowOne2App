package com.example.gabbinete.followone2.ui.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gabbinete.followone2.databinding.FragmentStandingsBinding
import com.google.android.material.tabs.TabLayoutMediator

class StandingsFragment : Fragment() {

    private var _binding: FragmentStandingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: StandingsTabAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStandingsBinding.inflate(inflater, container, false)
        adapter = StandingsTabAdapter(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewPager.adapter = adapter
            TabLayoutMediator(standingsTab, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Drivers"
                    1 -> tab.text = "Constructors"
                }
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}