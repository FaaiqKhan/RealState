package com.example.realstate.presentation

import androidx.lifecycle.*
import com.example.realstate.data.PropertyDetailUIData
import com.example.realstate.usecases.GetPropertyDetailsUseCase
import com.example.realstate.viewStates.PropertyDetailViewState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class PropertyDetailListViewModel @Inject constructor(
    private val propertyDetailsUseCase: GetPropertyDetailsUseCase
): ViewModel() {

    private val _propertyDetailsUIData = MutableLiveData<PropertyDetailUIData>()
    val propertyDetailsUIData: LiveData<PropertyDetailUIData> get() = _propertyDetailsUIData

    private val _propertyDetailsViewState: MutableLiveData<PropertyDetailViewState> by lazy {
        MutableLiveData()
    }
    val propertyDetailsViewState: LiveData<PropertyDetailViewState> get() = _propertyDetailsViewState

    fun fetchPropertyDetails(id: Int) {
        viewModelScope.launch {
            propertyDetailsUseCase(id).collectLatest {
                _propertyDetailsViewState.value = it
                if (it is PropertyDetailViewState.Success) {
                    _propertyDetailsUIData.value = it.item
                }
            }
        }
    }
}