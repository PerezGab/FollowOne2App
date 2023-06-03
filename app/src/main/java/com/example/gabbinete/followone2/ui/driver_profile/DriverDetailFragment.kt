package com.example.gabbinete.followone2.ui.driver_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gabbinete.followone2.databinding.FragmentDriverDetailBinding

class DriverDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDriverDetailBinding.inflate(inflater, container, false)

        return binding.root
    }
}