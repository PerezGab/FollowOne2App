package com.example.gabbinete.followone2.ui.standings

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gabbinete.followone2.entities.ConstructorStandings
import com.example.gabbinete.followone2.entities.DriverStandings
import com.example.gabbinete.followone2.entities.Standings
import com.example.gabbinete.followone.repo.toDomainDriver
import com.example.gabbinete.followone2.databinding.StandingListItemBinding

private const val TAG = "StandingsAdapter"

private const val TYPE_DRIVER = 1
private const val TYPE_CONSTRUCTOR = 2

class StandingsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = mutableListOf<Standings>()

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder is called")
        return when (viewType) {
            TYPE_DRIVER -> DriverStandingsViewHolder.from(parent)
            TYPE_CONSTRUCTOR -> ConstructorStandingsViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder is called")
        val item = list[position]
        when (holder) {
            is DriverStandingsViewHolder -> {
                holder.bind(item as DriverStandings)
            }
            is ConstructorStandingsViewHolder -> {
                holder.bind(item as ConstructorStandings)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        Log.d(TAG, "getItemViewType is called")
        return when (list[position]) {
            is DriverStandings -> TYPE_DRIVER
            is ConstructorStandings -> TYPE_CONSTRUCTOR
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    class ConstructorStandingsViewHolder private constructor(private val binding: StandingListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ConstructorStandings) {
            binding.finalPositionText.text = item.positionText
            binding.nameText.text = item.constructor.name
            binding.pointsText.text = item.points
            binding.constructorText.visibility = View.INVISIBLE
        }

        companion object {
            fun from(parent: ViewGroup): ConstructorStandingsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = StandingListItemBinding.inflate(layoutInflater, parent, false)

                return ConstructorStandingsViewHolder(binding)
            }
        }
    }

    class DriverStandingsViewHolder private constructor(private val binding: StandingListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DriverStandings) {
            Log.d(TAG, "DriverStandingsViewHolder.bind() is called")
            binding.finalPositionText.text = item.positionText
            item.driver.toDomainDriver().mapFlag?.let { binding.flagImage.setImageResource(it) }
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

    fun addStandings(standingsList: List<Standings>) {
        Log.d(TAG, "addStandings is called")
        list.addAll(standingsList)
        notifyDataSetChanged()
    }
}