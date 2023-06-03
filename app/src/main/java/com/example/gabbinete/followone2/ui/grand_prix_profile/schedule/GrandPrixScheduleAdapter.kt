package com.example.gabbinete.followone2.ui.grand_prix_profile.schedule

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gabbinete.followone2.databinding.GrandPrixSessionsListItemBinding
import com.example.gabbinete.followone2.domain.Session
import com.example.gabbinete.followone2.domain.SessionEnum

private const val TAG = "GrandPrixSchAdapter"

class GrandPrixScheduleAdapter :
    RecyclerView.Adapter<GrandPrixScheduleAdapter.GrandPrixScheduleViewHolder>() {

    val list = mutableListOf<Session>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrandPrixScheduleViewHolder =
        GrandPrixScheduleViewHolder.from(parent)

    override fun onBindViewHolder(holder: GrandPrixScheduleViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    class GrandPrixScheduleViewHolder(private val binding: GrandPrixSessionsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Session) {
            binding.sessionNameText.text = when (item.name) {
                SessionEnum.FP1 -> "Free Practice 1"
                SessionEnum.FP2 -> "Free Practice 2"
                SessionEnum.FP3 -> "Free Practice 3"
                SessionEnum.QUALY -> "Qualifying"
                SessionEnum.SPRINT -> "Sprint"
                SessionEnum.RACE -> "Race"
            }
            binding.sessionDateText.text = item.date
            binding.sessionTimeText.text = item.time
        }

        companion object {
            fun from(parent: ViewGroup): GrandPrixScheduleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    GrandPrixSessionsListItemBinding.inflate(layoutInflater, parent, false)

                return GrandPrixScheduleViewHolder(binding)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupSessions(vararg sessions: Session?) {
        list.clear()
        sessions.forEach {
            if (it != null) {
                list.add(it)
            }
        }
        Log.d(TAG, "List contains ${list.size} elements: $list")
        notifyDataSetChanged()
    }
}