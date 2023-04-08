package com.example.realstate.uiLayer.propertyList

import androidx.annotation.OpenForTesting
import androidx.lifecycle.*
import com.example.realstate.domainLayer.usecases.GetPropertyListUseCase
import com.example.realstate.uiLayer.viewStates.PropertyListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OpenForTesting
@HiltViewModel
class PropertyListViewModel @Inject constructor(
    private val propertyListUseCase: GetPropertyListUseCase
) : ViewModel() {

    private val _propertyListViewState =
        MutableStateFlow<PropertyListViewState>(PropertyListViewState.Loading)

    val propertyListViewState: StateFlow<PropertyListViewState> = _propertyListViewState

    init {
        fetchProperties()
    }

    final fun fetchProperties() {
        viewModelScope.launch {
            propertyListUseCase(Unit).collect {
                _propertyListViewState.value = it
            }
        }
    }
}