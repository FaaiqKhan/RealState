package com.example.realstate.repository

import com.example.realstate.data.*
import com.example.realstate.network.ErrorResponse
import com.example.realstate.network.NetworkResponse
import com.example.realstate.network.apiClient.PropertyDataApiClient
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class PropertyListDataRepositoryTest {

    private val apiClient: PropertyDataApiClient = mock()

    private val propertyListDataRepository = PropertyListDataRepository(apiClient)

    @Test
    fun `should return failure response when exception is thrown by fetchProperties`() = runBlocking {
        // given
        whenever(apiClient.fetchProperties()).thenThrow(RuntimeException("Whoops"))

        // when
        val result = propertyListDataRepository.getPropertyListData()

        // then
        Assert.assertEquals(result, NetworkResponse.Failure(ErrorResponse(-1, -1, message = "Whoops")))
    }

    @Test
    fun `should return success response when fetchProperties runs successfully`() = runBlocking {
        // given
        whenever(apiClient.fetchProperties()).thenReturn(PropertyListResponseData(dataList))

        // when
        val result = propertyListDataRepository.getPropertyListData()

        // then
        Assert.assertEquals(result, NetworkResponse.Success(PropertyListResponseData(dataList)))
    }

    @Test
    fun `should return failure response when exception is thrown by fetchPropertyDetails`() = runBlocking {
        // given
        whenever(apiClient.fetchPropertyDetails(1)).thenThrow(RuntimeException("Whoops"))

        // when
        val result = propertyListDataRepository.getPropertyDetails(1)

        // then
        Assert.assertEquals(result, NetworkResponse.Failure(ErrorResponse(-1, -1, message = "Whoops")))
    }

    @Test
    fun `should return success response when fetchPropertyDetails runs successfully`() = runBlocking {
        // given
        whenever(apiClient.fetchPropertyDetails(1)).thenReturn(data)

        // when
        val result = propertyListDataRepository.getPropertyDetails(1)

        // then
        Assert.assertEquals(result, NetworkResponse.Success(data))
    }

    private val data = PropertyResponseData(
        id = 1,
        city = "Villers-sur-Mer",
        bedrooms = 4,
        area = 250.0,
        price = 150000.0,
        professional = "Steve",
        propertyType = "House",
        offerType = 1,
        rooms = 8,
    )

    private val dataList = listOf(data)

}