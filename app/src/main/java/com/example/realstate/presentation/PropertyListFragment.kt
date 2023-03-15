package com.example.realstate.presentation

import android.os.Bundle
import android.view.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.realstate.ui.theme.RealStateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PropertyListFragment : Fragment() {

    private val propertyListViewModel: PropertyListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                RealStateTheme {
                    val state by propertyListViewModel.propertyListViewState.observeAsState()
                    state?.let { it ->
                        PropertyListView(listViewState = it) { data ->
                            navigateToDetails(data.id)
                        }
                    }
                }
            }
        }
    }

    private fun navigateToDetails(id: Int) {
        view?.let {
            Navigation.findNavController(it).navigate(
                PropertyListFragmentDirections
                    .actionPropertyListFragmentToPropertyDetailsFragment(id)
            )
        }
    }
}