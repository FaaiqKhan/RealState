package com.example.realstate.presentation

import androidx.lifecycle.*
import com.example.realstate.usecases.GetPropertyListUseCase
import com.example.realstate.viewStates.PropertyListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyListViewModel @Inject constructor(
    private val propertyListUseCase: GetPropertyListUseCase
): ViewModel() {

    private val _propertyListViewState: MutableLiveData<PropertyListViewState> by lazy {
        MutableLiveData()
    }
    val propertyListViewState: LiveData<PropertyListViewState> get() = _propertyListViewState

    init {
        fetchProperties()
    }

    fun fetchProperties() {
        viewModelScope.launch {
            propertyListUseCase(Unit).collectLatest {
                _propertyListViewState.value = it
            }
        }
    }
}