package com.example.gabbinete.followone2.ui.standings.drivers

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gabbinete.followone2.databinding.StandingListItemBinding
import com.example.gabbinete.followone2.domain.Driver
import com.example.gabbinete.followone2.domain.DriverStandings
import com.example.gabbinete.followone2.domain.Standings

private const val TAG = "DriverStandingsAdapter"

class StandingsAdapter(private val clickListener: DriverListener) :
    RecyclerView.Adapter<StandingsAdapter.DriverStandingsViewHolder>() {

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
        holder.bind(list[position] as DriverStandings, clickListener)
    }

    class DriverStandingsViewHolder private constructor(private val binding: StandingListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DriverStandings, clickListener: DriverListener) {
            Log.d(TAG, "DriverStandingsViewHolder.bind() is called")

            binding.apply {
                finalPositionText.text = item.positionText
                this.clickListener = clickListener
                item.driver.mapFlag?.let { flagImage.setImageResource(it) }
                nameText.text = item.driver.familyName
                constructorText.text = item.constructors[0].name
                pointsText.text = item.points
            }
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
        list.clear()
        Log.d(TAG, "addStandings is called")
        list.addAll(standingsList)
        notifyDataSetChanged()
    }
}

class DriverListener(val clickListener: (item: Driver) -> Unit) {
    fun onClick(driver: Driver) = clickListener(driver)
}