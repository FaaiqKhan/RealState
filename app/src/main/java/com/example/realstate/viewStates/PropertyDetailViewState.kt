package com.example.realstate.viewStates

import com.example.realstate.data.PropertyDetailUIData

sealed class PropertyDetailViewState {
    data class Success(val item: PropertyDetailUIData) : PropertyDetailViewState()
    data class Failure(val message: String) : PropertyDetailViewState()
    object Loading : PropertyDetailViewState()
}