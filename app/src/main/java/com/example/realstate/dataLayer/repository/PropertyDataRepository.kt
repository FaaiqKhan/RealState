package com.example.realstate.dataLayer.repository

import com.example.realstate.dataLayer.data.PropertyListResponseData
import com.example.realstate.dataLayer.data.PropertyResponseData
import com.example.realstate.dataLayer.network.ErrorResponse
import com.example.realstate.dataLayer.network.NetworkResponse
import com.example.realstate.dataLayer.network.apiClient.PropertyDataApiClient
import javax.inject.Inject

interface PropertyDataRepository {
    suspend fun getPropertyListData() : NetworkResponse<PropertyListResponseData>
    suspend fun getPropertyDetails(id: Int): NetworkResponse<PropertyResponseData>
}

class PropertyListDataRepository @Inject constructor(
    private val service: PropertyDataApiClient
): PropertyDataRepository {

    override suspend fun getPropertyListData(): NetworkResponse<PropertyListResponseData> {
        return try {
            val result = service.fetchProperties()
            NetworkResponse.Success(result)
        } catch (throwable: Throwable) {
            NetworkResponse.Failure(createUnexpectedError(throwable))
        }
    }

    override suspend fun getPropertyDetails(id: Int): NetworkResponse<PropertyResponseData> {
        return try {
            val result = service.fetchPropertyDetails(id = id)
            NetworkResponse.Success(result)
        } catch (throwable: Throwable) {
            NetworkResponse.Failure(createUnexpectedError(throwable))
        }
    }

    private fun createUnexpectedError(throwable: Throwable) =
        ErrorResponse(-1, -1, throwable.message)

}