package com.example.gabbinete.followone2.ui.standings.drivers

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gabbinete.followone2.databinding.StandingListItemBinding
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.domain.Standings

private const val TAG = "StandingsAdapter"

class StandingsAdapter : RecyclerView.Adapter<StandingsAdapter.DriverStandingsViewHolder>() {

    private val list = mutableListOf<Standings>()

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount is called. The list size is ${list.size}")
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverStandingsViewHolder {
        Log.d(TAG, "onCreateViewHolder is called")
        return DriverStandingsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DriverStandingsViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder is called")
        holder.bind(list[position] as DriverStandings)
    }

    class DriverStandingsViewHolder private constructor(private val binding: StandingListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DriverStandings) {
            Log.d(TAG, "DriverStandingsViewHolder.bind() is called")
            binding.finalPositionText.text = item.positionText
            item.driver.mapFlag?.let { binding.flagImage.setImageResource(it) }
            binding.nameText.text = item.driver.familyName
            binding.constructorText.text = item.constructors[0].name
            binding.pointsText.text = item.points
        }

        companion object {
            fun from(parent: ViewGroup): DriverStandingsViewHolder {
                Log.d(TAG, "DriverStandingViewHolder inflater is called")
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = StandingListItemBinding.inflate(layoutInflater, parent, false)

                return DriverStandingsViewHolder(binding)
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun addStandings(standingsList: List<Standings>) {
        Log.d(TAG, "addStandings is called")
        list.addAll(standingsList)
        notifyDataSetChanged()
    }
}