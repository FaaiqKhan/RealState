package com.example.realstate.uiLayer.viewStates

import com.example.realstate.dataLayer.data.PropertyListUIData

sealed class PropertyListViewState {
    data class Success(val items: List<PropertyListUIData>) : PropertyListViewState()
    data class Failure(val message: String) : PropertyListViewState()
    object Loading : PropertyListViewState()
}