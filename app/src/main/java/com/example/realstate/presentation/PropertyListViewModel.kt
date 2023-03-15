package com.example.realstate.presentation

import androidx.lifecycle.*
import com.example.realstate.data.PropertyListUIData
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

    private val _propertyListUIData = MutableLiveData<List<PropertyListUIData>>()
    val propertyListUIData: LiveData<List<PropertyListUIData>> get() = _propertyListUIData

    private val _propertyListViewState: MutableLiveData<PropertyListViewState> by lazy {
        MutableLiveData()
    }
    val propertyListViewState: LiveData<PropertyListViewState> get() = _propertyListViewState

    fun fetchProperties() {
        viewModelScope.launch {
            propertyListUseCase(null).collectLatest {
                _propertyListViewState.value = it
                if (it is PropertyListViewState.Success) {
                    _propertyListUIData.value = it.items
                }
            }
        }
    }
}