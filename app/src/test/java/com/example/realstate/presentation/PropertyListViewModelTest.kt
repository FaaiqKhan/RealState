package com.example.realstate.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.realstate.data.PropertyListUIData
import com.example.realstate.usecases.GetPropertyListUseCase
import com.example.realstate.viewStates.PropertyListViewState
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
class PropertyListViewModelTest {

    private val propertyListUseCase: GetPropertyListUseCase = mock()

    private lateinit var propertyListViewModel: PropertyListViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val scheduler = TestCoroutineScheduler()
    private val dispatcher = UnconfinedTestDispatcher(scheduler)

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        propertyListViewModel = PropertyListViewModel(propertyListUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should update viewState with Loading when use-case returns Loading`() {
        whenever(propertyListUseCase(Unit)).thenReturn(
            flow {
                emit(PropertyListViewState.Loading)
            }
        )

        propertyListViewModel.fetchProperties()

        Assert.assertNotNull(propertyListViewModel.propertyListViewState.value is PropertyListViewState.Loading)
    }

    @Test
    fun `should update viewState with success when use-case returns success`() {
        whenever(propertyListUseCase(Unit)).thenReturn(
            flow {
                emit(PropertyListViewState.Success(listOf(uiData)))
            }
        )

        propertyListViewModel.fetchProperties()

        Assert.assertNotNull(propertyListViewModel.propertyListViewState.value is PropertyListViewState.Success)
    }

    @Test
    fun `should update viewState with failure when use-case returns failure`() {
        whenever(propertyListUseCase(Unit)).thenReturn(
            flow {
                emit(PropertyListViewState.Failure(message = ""))
            }
        )

        propertyListViewModel.fetchProperties()

        Assert.assertNotNull(propertyListViewModel.propertyListViewState.value is PropertyListViewState.Failure)
    }

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
