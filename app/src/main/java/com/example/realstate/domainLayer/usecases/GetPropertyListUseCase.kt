package com.example.realstate.domainLayer.usecases

import com.example.realstate.dataLayer.data.PropertyListResponseData
import com.example.realstate.dataLayer.data.PropertyListUIData
import com.example.realstate.dataLayer.network.NetworkResponse
import com.example.realstate.dataLayer.repository.PropertyDataRepository
import com.example.realstate.domainLayer.utils.FLowUseCase
import com.example.realstate.uiLayer.viewStates.PropertyListViewState
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface GetPropertyListUseCase: FLowUseCase<Unit, PropertyListViewState>

class DefaultGetPropertyListUseCase @Inject constructor(
    private val propertyDataRepository: PropertyDataRepository
): GetPropertyListUseCase {

    override fun invoke(data: Unit): Flow<PropertyListViewState> = flow {
        emit(PropertyListViewState.Loading)
        emit(
            when (val response = propertyDataRepository.getPropertyListData()) {
                is NetworkResponse.Success -> onSuccess(response)
                is NetworkResponse.Failure -> onFailure(response)
            }
        )
    }

    private fun onSuccess(result: NetworkResponse.Success<PropertyListResponseData>) =
        PropertyListViewState.Success(result.data.items.map {
            PropertyListUIData(
                id = it.id,
                image = it.imageUrl,
                price = it.price.toString(),
                city = it.city,
                rooms = it.rooms.toString(),
                bedrooms = it.bedrooms.toString(),
                area = it.area.toString()
            )
        })

    private fun onFailure(result: NetworkResponse.Failure) =
        PropertyListViewState.Failure(result.error.message ?: "Unexpected error")
}