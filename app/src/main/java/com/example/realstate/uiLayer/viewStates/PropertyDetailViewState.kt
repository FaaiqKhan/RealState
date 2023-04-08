package com.example.realstate.uiLayer.viewStates

import com.example.realstate.dataLayer.data.PropertyDetailUIData

sealed class PropertyDetailViewState {
    data class Success(val item: PropertyDetailUIData) : PropertyDetailViewState()
    data class Failure(val message: String) : PropertyDetailViewState()
    object Loading : PropertyDetailViewState()
}