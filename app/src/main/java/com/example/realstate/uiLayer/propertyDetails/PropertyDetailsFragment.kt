package com.example.realstate.uiLayer.propertyDetails

import android.os.Bundle
import android.view.*
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PropertyDetailsFragment: Fragment() {

    private val propertyDetailsViewModel: PropertyDetailViewModel by viewModels()

    private val args: PropertyDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Surface {
                    val state by propertyDetailsViewModel.propertyDetailsViewState.observeAsState()
                    state?.let { PropertyDetailView(detailViewState = it) }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        propertyDetailsViewModel.fetchPropertyDetails(args.propertyId)
    }
}