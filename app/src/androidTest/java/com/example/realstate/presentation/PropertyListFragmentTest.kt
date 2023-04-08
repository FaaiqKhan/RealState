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
import com.example.realstate.uiLayer.propertyList.PropertyListViewModel
import com.example.realstate.uiLayer.viewStates.PropertyListViewState
import com.nhaarman.mockitokotlin2.*
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class PropertyListFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        activityRule.scenario.onActivity {
            it.findNavController(R.id.nav_host_fragment_container)
                .navigate(R.id.propertyListFragment)
        }

        viewModel = mock {
            on { propertyListViewState} doReturn MutableLiveData()
        }
    }

    @BindValue
    @Mock
    lateinit var viewModel: PropertyListViewModel

    private val PropertyListViewModel.mutableViewState
        get() = propertyListViewState as MutableLiveData<PropertyListViewState>


    @Test
    fun shouldShowLoaderWhenUIStateIsLoading() {
        UiThreadStatement.runOnUiThread {
            viewModel.mutableViewState.value = PropertyListViewState.Loading
        }

        composeTestRule.onNodeWithTag("Loader").assertIsDisplayed()
    }

    @Test
    fun shouldShowErrorMessageWhenUIStateIsFailure() {
        UiThreadStatement.runOnUiThread {
            viewModel.mutableViewState.value = PropertyListViewState.Failure("Error")
        }

        composeTestRule.onNodeWithTag("Error").assertIsDisplayed()
    }

    @Test
    fun shouldShowDataWhenUIStateIsSuccess() {
        UiThreadStatement.runOnUiThread {
            viewModel.mutableViewState.value = PropertyListViewState.Success(listOf())
        }

        composeTestRule.onNodeWithTag("data").assertIsDisplayed()
    }

}