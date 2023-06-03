package com.example.gabbinete.followone2.ui.grand_prix_profile.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gabbinete.followone2.R
import com.example.gabbinete.followone2.databinding.FragmentGrandPrixScheduleBinding
import com.example.gabbinete.followone2.domain.Session
import com.example.gabbinete.followone2.ui.grand_prix_profile.GrandPrixProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "GrandPrixScheduleFrag"

@AndroidEntryPoint
class GrandPrixScheduleFragment : Fragment() {

    private var _binding: FragmentGrandPrixScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: GrandPrixScheduleAdapter

    private val viewModel: GrandPrixProfileViewModel by navGraphViewModels(R.id.grandPrixProfileFragment) { defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGrandPrixScheduleBinding.inflate(inflater, container, false)
        adapter = GrandPrixScheduleAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated is called.")

        binding.recyclerView.apply {
            adapter = this@GrandPrixScheduleFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    Log.d(TAG, "GrandPrix value is ${it.grandPrix?.raceName}")
                    it.grandPrix?.let { grandPrix ->
                        binding.grandPrixTitle.text = grandPrix.formalTitleName
                        binding.grandPrixRoundText.text = "Round ${grandPrix.round}"
                        val fp1: Session? = grandPrix.firstPractice
                        val fp2: Session? = grandPrix.secondPractice
                        val fp3: Session? = grandPrix.thirdPractice
                        val qualy: Session? = grandPrix.qualifying
                        val sprint: Session? = grandPrix.sprint
                        val race: Session = grandPrix.race
                        adapter.setupSessions(fp1, fp2, fp3, qualy, sprint, race)
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}