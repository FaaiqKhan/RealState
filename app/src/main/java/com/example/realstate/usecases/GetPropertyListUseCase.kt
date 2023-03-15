package com.example.realstate.usecases

import com.example.realstate.data.*
import com.example.realstate.network.NetworkResponse
import com.example.realstate.repository.PropertyDataRepository
import com.example.realstate.utils.FLowUseCase
import com.example.realstate.viewStates.PropertyListViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetPropertyListUseCase: FLowUseCase<Unit, PropertyListViewState>

class DefaultGetPropertyListUseCase @Inject constructor(
    private val propertyDataRepository: PropertyDataRepository
): GetPropertyListUseCase {

    override fun invoke(id: Unit?): Flow<PropertyListViewState> = flow {
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
                price = it.price,
                city = it.city,
                rooms = it.rooms,
                bedrooms = it.bedrooms,
                area = it.area
            )
        })

    private fun onFailure(result: NetworkResponse.Failure) =
        PropertyListViewState.Failure(result.error.message ?: "Unexpected error")
}