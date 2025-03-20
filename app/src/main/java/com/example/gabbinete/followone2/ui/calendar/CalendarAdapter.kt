package com.example.gabbinete.followone2.ui.calendar

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gabbinete.followone2.databinding.GrandprixListItemBinding
import com.example.gabbinete.followone2.domain.GrandPrix
import com.example.gabbinete.followone2.util.formatDate

private const val TAG = "CalendarAdapter"

class CalendarAdapter(private val clickListener: GrandPrixListener) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private val gpList = mutableListOf<GrandPrix>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        Log.d(TAG, "onCreateViewHolder is called")
        return CalendarViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder is called")
        holder.bind(gpList[position], clickListener)
    }

    override fun getItemCount(): Int = gpList.size

    class CalendarViewHolder private constructor(private val binding: GrandprixListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GrandPrix, clickListener: GrandPrixListener) {
            Log.d(TAG, "bind() is called")
            binding.apply {
                grandPrix = item
                this.clickListener = clickListener
                dateText.text = item.date?.formatDate()
                grandPrixNameText.text = item.raceName
                circuitName.text = item.circuit?.circuitName
                "Round ${item.round}".also { roundText.text = it }
            }
        }

        companion object {
            fun from(parent: ViewGroup): CalendarViewHolder {
                Log.d(TAG, "CalendarViewHolder inflater is called")
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GrandprixListItemBinding.inflate(layoutInflater, parent, false)

                return CalendarViewHolder(binding)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupAdapter(list: List<GrandPrix>) {
        gpList.clear()
        Log.d(TAG, "setupAdapter is called")
        gpList.addAll(list)
        Log.d(TAG, "The list size is ${list.size}")

        notifyDataSetChanged()
    }
}

class GrandPrixListener(val clickListener: (item: GrandPrix) -> Unit) {
    fun onClick(gp: GrandPrix) = clickListener(gp)
}