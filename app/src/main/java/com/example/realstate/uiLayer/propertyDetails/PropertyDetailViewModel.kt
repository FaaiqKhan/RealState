package com.example.realstate.uiLayer.propertyDetails

import androidx.annotation.OpenForTesting
import androidx.lifecycle.*
import com.example.realstate.domainLayer.usecases.GetPropertyDetailsUseCase
import com.example.realstate.uiLayer.viewStates.PropertyDetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@OpenForTesting
@HiltViewModel
class PropertyDetailViewModel @Inject constructor(
    private val propertyDetailsUseCase: GetPropertyDetailsUseCase
): ViewModel() {

    private val _propertyDetailsViewState: MutableLiveData<PropertyDetailViewState> by lazy {
        MutableLiveData()
    }
    val propertyDetailsViewState: LiveData<PropertyDetailViewState> get() = _propertyDetailsViewState

    fun fetchPropertyDetails(id: Int) {
        viewModelScope.launch {
            propertyDetailsUseCase(id).collectLatest {
                _propertyDetailsViewState.value = it
            }
        }
    }
}