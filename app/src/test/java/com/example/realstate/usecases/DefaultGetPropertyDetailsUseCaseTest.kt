package com.example.realstate.usecases

import com.example.realstate.data.*
import com.example.realstate.network.ErrorResponse
import com.example.realstate.network.NetworkResponse
import com.example.realstate.repository.PropertyDataRepository
import com.example.realstate.viewStates.PropertyDetailViewState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DefaultGetPropertyDetailsUseCaseTest {

    private val propertyDataRepository: PropertyDataRepository = mock()

    private val propertyListUseCase = DefaultGetPropertyDetailsUseCase(propertyDataRepository)

    @Test
    fun `execute GetPropertyDetailsUseCase and expect failure`() = runBlocking {
        val responseResult = NetworkResponse.Failure(ErrorResponse(message = ""))

        val expectedUIStates = arrayListOf<PropertyDetailViewState>().apply {
            add(PropertyDetailViewState.Loading)
            add(PropertyDetailViewState.Failure(""))
        }.toTypedArray()

        // given
        whenever(propertyDataRepository.getPropertyDetails(1)).thenReturn(responseResult)

        // when
        val uiStates = propertyListUseCase.invoke(1).toList().toTypedArray()

        // then
        Assert.assertNotNull(uiStates)
        Assert.assertArrayEquals(expectedUIStates, uiStates)
    }

    @Test
    fun `execute to get data use-case and expect success`() = runBlocking {
        val responseResult = NetworkResponse.Success(data)

        val expectedUIStates = arrayListOf<PropertyDetailViewState>().apply {
            add(PropertyDetailViewState.Loading)
            add(PropertyDetailViewState.Success(uiData))
        }.toTypedArray()

        // given
        whenever(propertyDataRepository.getPropertyDetails(1)).thenReturn(responseResult)

        // when
        val uiStates = propertyListUseCase.invoke(1).toList().toTypedArray()

        // then
        Assert.assertNotNull(uiStates)
        Assert.assertArrayEquals(expectedUIStates, uiStates)
    }

    private val data = PropertyResponseData(
        id = 1,
        city = "Villers-sur-Mer",
        imageUrl = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
        bedrooms = 4,
        area = 250.0,
        price = 150000.0,
        professional = "Steve",
        propertyType = "House",
        offerType = 1,
        rooms = 8,
    )

    private val uiData = PropertyDetailUIData(
        id = 1,
        image = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
        price = "150000.0",
        city = "Villers-sur-Mer",
        rooms = "8",
        bedrooms = "4",
        area = "250.0",
        offerType = "1",
        professional = "Steve",
        propertyType = "House",
    )
}