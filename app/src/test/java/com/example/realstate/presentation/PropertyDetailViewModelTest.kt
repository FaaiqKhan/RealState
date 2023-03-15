package com.example.realstate.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.realstate.data.PropertyDetailUIData
import com.example.realstate.usecases.GetPropertyDetailsUseCase
import com.example.realstate.viewStates.PropertyDetailViewState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class PropertyDetailViewModelTest {

    private val propertyDetailsUseCase: GetPropertyDetailsUseCase = mock()

    private lateinit var propertyDetailViewModel: PropertyDetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val scheduler = TestCoroutineScheduler()
    private val dispatcher = UnconfinedTestDispatcher(scheduler)

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        propertyDetailViewModel = PropertyDetailViewModel(propertyDetailsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should update viewState with Loading when use-case returns Loading`() {
        // given
        whenever(propertyDetailsUseCase(1)).thenReturn(
            flow {
                emit(PropertyDetailViewState.Loading)
            }
        )

        // when
        propertyDetailViewModel.fetchPropertyDetails(1)

        // then
        Assert.assertNotNull(propertyDetailViewModel.propertyDetailsViewState.value is PropertyDetailViewState.Loading)
    }

    @Test
    fun `should update viewState with success when use-case returns success`() {
        // given
        whenever(propertyDetailsUseCase(1)).thenReturn(
            flow {
                emit(PropertyDetailViewState.Success(uiData))
            }
        )

        // when
        propertyDetailViewModel.fetchPropertyDetails(1)

        // then
        Assert.assertNotNull(propertyDetailViewModel.propertyDetailsViewState.value is PropertyDetailViewState.Success)
    }

    @Test
    fun `should update viewState with failure when use-case returns failure`() {
        // given
        whenever(propertyDetailsUseCase(1)).thenReturn(
            flow {
                emit(PropertyDetailViewState.Failure(message = ""))
            }
        )

        // when
        propertyDetailViewModel.fetchPropertyDetails(1)

        // then
        Assert.assertNotNull(propertyDetailViewModel.propertyDetailsViewState.value is PropertyDetailViewState.Failure)
    }

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