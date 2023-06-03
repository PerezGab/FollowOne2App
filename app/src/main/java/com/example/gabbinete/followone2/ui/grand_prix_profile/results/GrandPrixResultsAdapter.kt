package com.example.gabbinete.followone2.ui.grand_prix_profile.results

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gabbinete.followone2.databinding.GrandPrixResultsListItemBinding
import com.example.gabbinete.followone2.domain.RaceResults

private const val TAG = "GrandPrixResultsAdapter"

class GrandPrixResultsAdapter :
    RecyclerView.Adapter<GrandPrixResultsAdapter.GrandPrixResultsViewHolder>() {

    private val list = mutableListOf<RaceResults>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrandPrixResultsViewHolder {
        return GrandPrixResultsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GrandPrixResultsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class GrandPrixResultsViewHolder private constructor(private val binding: GrandPrixResultsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RaceResults) {
            Log.d(TAG, "GrandPrixResultsViewHolder.bind() is called")
            val constructor = item.constructor
            binding.apply {
                finalPositionText.text = item.positionText
                constructor.logo?.let { teamImage.setImageResource(it) }
                nameText.text = item.driver.familyName
                timeText.text =
                    if (item.positionText == "R") "DNF" else item.time?.time ?: item.status
            }
        }

        companion object {
            fun from(parent: ViewGroup): GrandPrixResultsViewHolder {
                Log.d(TAG, "GrandPrixResultsViewHolder.from() is called.")
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GrandPrixResultsListItemBinding.inflate(layoutInflater, parent, false)

                return GrandPrixResultsViewHolder(binding)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupRaceResults(results: List<RaceResults>) {
        list.clear()
        list.addAll(results)
        Log.d(TAG, "List contains: $list")
        notifyDataSetChanged()
    }
}