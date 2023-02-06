package com.example.gabbinete.followone2.ui.calendar

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gabbinete.followone2.databinding.GrandprixListItemBinding
import com.example.gabbinete.followone2.entities.GrandPrix

class CalendarAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val gpList = mutableListOf<GrandPrix>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = gpList.size

    class CalendarViewHolder private constructor(private val binding: GrandprixListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GrandPrix) {

        }
    }
}