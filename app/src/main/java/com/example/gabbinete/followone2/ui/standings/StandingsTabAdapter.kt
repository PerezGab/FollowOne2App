package com.example.gabbinete.followone2.ui.standings

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gabbinete.followone2.ui.standings.constructor.ConstructorStandingsFragment
import com.example.gabbinete.followone2.ui.standings.drivers.DriverStandingsFragment

class StandingsTabAdapter(fragment: StandingsFragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> DriverStandingsFragment()
            else -> ConstructorStandingsFragment()
        }
    }
}