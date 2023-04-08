package com.example.realstate.domainLayer.usecases

import androidx.compose.ui.unit.dp
import com.example.realstate.dataLayer.data.PropertyDetailUIData
import com.example.realstate.dataLayer.data.PropertyResponseData
import com.example.realstate.dataLayer.network.NetworkResponse
import com.example.realstate.dataLayer.repository.PropertyDataRepository
import com.example.realstate.domainLayer.utils.FLowUseCase
import com.example.realstate.uiLayer.viewStates.PropertyDetailViewState
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
                bedrooms = result.data.bedrooms.toString(),
                area = result.data.area.toString(),
                image = result.data.imageUrl,
                price = result.data.price.toString(),
                professional = result.data.professional,
                propertyType = result.data.propertyType,
                offerType = result.data.offerType.toString(),
                rooms = result.data.rooms.toString()
            )
        )

    private fun onFailure(result: NetworkResponse.Failure) =
        PropertyDetailViewState.Failure(result.error.message ?: "Unexpected error")
}