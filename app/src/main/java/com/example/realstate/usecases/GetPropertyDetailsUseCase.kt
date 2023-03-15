package com.example.realstate.usecases

import com.example.realstate.data.PropertyDetailUIData
import com.example.realstate.data.PropertyResponseData
import com.example.realstate.network.NetworkResponse
import com.example.realstate.repository.PropertyDataRepository
import com.example.realstate.utils.FLowUseCase
import com.example.realstate.viewStates.PropertyDetailViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetPropertyDetailsUseCase : FLowUseCase<Int, PropertyDetailViewState>

class DefaultGetPropertyDetailsUseCase @Inject constructor(
    private val propertyDataRepository: PropertyDataRepository
) : GetPropertyDetailsUseCase {

    override fun invoke(id: Int): Flow<PropertyDetailViewState> = flow {
        emit(PropertyDetailViewState.Loading)
        emit(
            when (val response = propertyDataRepository.getPropertyDetails(id)) {
                is NetworkResponse.Success -> onSuccess(response)
                is NetworkResponse.Failure -> onFailure(response)
            }
        )
    }

    private fun onSuccess(result: NetworkResponse.Success<PropertyResponseData>) =
        PropertyDetailViewState.Success(
            PropertyDetailUIData(
                id = result.data.id,
                city = result.data.city,
                bedrooms = result.data.bedrooms ?: 0,
                area = result.data.area,
                image = result.data.imageUrl.orEmpty(),
                price = result.data.price,
                professional = result.data.professional,
                propertyType = result.data.propertyType,
                offerType = result.data.offerType,
                rooms = result.data.rooms ?: 0
            )
        )

    private fun onFailure(result: NetworkResponse.Failure) =
        PropertyDetailViewState.Failure(result.error.message ?: "Unexpected error")
}