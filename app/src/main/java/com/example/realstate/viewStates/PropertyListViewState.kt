package com.example.realstate.viewStates

import com.example.realstate.data.PropertyListUIData

sealed class PropertyListViewState {
    data class Success(val items: PropertyListUIData) : PropertyListViewState()
    data class Failure(val message: String) : PropertyListViewState()
    object Loading : PropertyListViewState()
}