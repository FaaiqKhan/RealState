package com.example.realstate.viewStates

import com.example.realstate.data.PropertyUIData

sealed class PropertyListViewState {
    data class Success(val items: List<PropertyUIData>) : PropertyListViewState()
    data class Failure(val message: String) : PropertyListViewState()
    object Loading : PropertyListViewState()
}