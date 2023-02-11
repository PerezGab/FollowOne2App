package com.example.gabbinete.followone2.ui.standings.constructor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gabbinete.followone.repo.toSingleDomainConstructor
import com.example.gabbinete.followone2.databinding.StandingListItemBinding
import com.example.gabbinete.followone2.entities.ConstructorStandings
import com.example.gabbinete.followone2.entities.Standings

class ConstructorStandingsAdapter :
    RecyclerView.Adapter<ConstructorStandingsAdapter.ConstructorStandingsViewHolder>() {

    private val list = mutableListOf<Standings>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConstructorStandingsViewHolder = ConstructorStandingsViewHolder.from(parent)

    override fun onBindViewHolder(holder: ConstructorStandingsViewHolder, position: Int) =
        holder.bind(list[position] as ConstructorStandings)

    override fun getItemCount(): Int = list.size

    class ConstructorStandingsViewHolder private constructor(private val binding: StandingListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ConstructorStandings) {
            item.constructor.toSingleDomainConstructor().logo?.let { binding.flagImage.setImageResource(it) }
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

    @SuppressLint("NotifyDataSetChanged")
    fun addStandings(standingsList: List<Standings>){
        list.addAll(standingsList)
        notifyDataSetChanged()
    }
}