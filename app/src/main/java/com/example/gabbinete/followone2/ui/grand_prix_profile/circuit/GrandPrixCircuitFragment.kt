package com.example.gabbinete.followone2.ui.grand_prix_profile.circuit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import com.example.gabbinete.followone2.R
import com.example.gabbinete.followone2.databinding.FragmentGrandPrixCircuitBinding
import com.example.gabbinete.followone2.ui.grand_prix_profile.GrandPrixProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GrandPrixCircuitFragment : Fragment() {

    private var _binding: FragmentGrandPrixCircuitBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GrandPrixProfileViewModel by navGraphViewModels(R.id.grandPrixProfileFragment) { defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGrandPrixCircuitBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect {
                it.grandPrix?.let { grandPrix ->
                    binding.apply {
                        grandPrixNameText.text = grandPrix.circuit?.circuitName
                        grandPrix.circuit?.location?.countryFlag?.let { flag ->
                            grandPrixFlag.setImageResource(flag)
                        }
                        grandPrix.circuit?.layout?.let { layout ->
                            grandPrixCircuitImage.setImageResource(layout)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}