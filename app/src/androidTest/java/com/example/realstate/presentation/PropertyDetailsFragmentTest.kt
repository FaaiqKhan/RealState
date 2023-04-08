package com.example.realstate.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.example.realstate.MainActivity
import com.example.realstate.R
import com.example.realstate.dataLayer.data.PropertyDetailUIData
import com.example.realstate.uiLayer.propertyDetails.PropertyDetailViewModel
import com.example.realstate.uiLayer.viewStates.PropertyDetailViewState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class PropertyDetailsFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        activityRule.scenario.onActivity {
            it.findNavController(R.id.nav_host_fragment_container)
                .navigate(R.id.propertyDetailsFragment, PropertyDetailsFragmentArgs(1).toBundle())
        }

        viewModel = mock {
            on { propertyDetailsViewState} doReturn MutableLiveData()
        }
    }

    @BindValue
    @Mock
    lateinit var viewModel: PropertyDetailViewModel

    private val PropertyDetailViewModel.mutableViewState
        get() = propertyDetailsViewState as MutableLiveData<PropertyDetailViewState>


    @Test
    fun shouldShowLoaderWhenUIStateIsLoading() {
        UiThreadStatement.runOnUiThread {
            viewModel.mutableViewState.value = PropertyDetailViewState.Loading
        }

        composeTestRule.onNodeWithTag("Loader").assertIsDisplayed()
    }

    @Test
    fun shouldShowErrorMessageWhenUIStateIsFailure() {
        UiThreadStatement.runOnUiThread {
            viewModel.mutableViewState.value = PropertyDetailViewState.Failure("Error")
        }

        composeTestRule.onNodeWithTag("Error").assertIsDisplayed()
    }

    @Test
    fun shouldShowDataWhenUIStateIsSuccess() {
        UiThreadStatement.runOnUiThread {
            viewModel.mutableViewState.value = PropertyDetailViewState.Success(uiData)
        }

        composeTestRule.onNodeWithTag("Data").assertIsDisplayed()
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