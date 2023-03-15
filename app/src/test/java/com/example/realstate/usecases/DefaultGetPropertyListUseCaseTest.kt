package com.example.realstate.usecases

import com.example.realstate.data.*
import com.example.realstate.network.ErrorResponse
import com.example.realstate.network.NetworkResponse
import com.example.realstate.repository.PropertyDataRepository
import com.example.realstate.viewStates.PropertyListViewState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DefaultGetPropertyListUseCaseTest {

    private val propertyDataRepository: PropertyDataRepository = mock()

    private val propertyListUseCase = DefaultGetPropertyListUseCase(propertyDataRepository)

    @Test
    fun `execute GetPropertyDetailsUseCase and expect failure`() = runBlocking {
        val responseResult = NetworkResponse.Failure(ErrorResponse(message = ""))

        val expectedUIStates = arrayListOf<PropertyListViewState>().apply {
            add(PropertyListViewState.Loading)
            add(PropertyListViewState.Failure(""))
        }.toTypedArray()

        whenever(propertyDataRepository.getPropertyListData()).thenReturn(responseResult)

        val uiStates = propertyListUseCase.invoke(Unit).toList().toTypedArray()

        Assert.assertNotNull(uiStates)
        Assert.assertArrayEquals(expectedUIStates, uiStates)
    }

    @Test
    fun `execute get data use-case and expect success`() = runBlocking {
        val responseResult = NetworkResponse.Success(PropertyListResponseData(listOf(data)))

        val expectedUIStates = arrayListOf<PropertyListViewState>().apply {
            add(PropertyListViewState.Loading)
            add(PropertyListViewState.Success(listOf(uiData)))
        }.toTypedArray()

        whenever(propertyDataRepository.getPropertyListData()).thenReturn(responseResult)

        val uiStates = propertyListUseCase.invoke(Unit).toList().toTypedArray()

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

    private val uiData = PropertyListUIData(
        id = 1,
        image = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
        price = "150000.0",
        city = "Villers-sur-Mer",
        rooms = "8",
        bedrooms = "4",
        area = "250.0"
    )
}