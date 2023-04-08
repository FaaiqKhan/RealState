package com.example.realstate.uiLayer.propertyList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realstate.dataLayer.data.PropertyListUIData
import com.example.realstate.uiLayer.presentation.PropertyItemLayout
import com.example.realstate.uiLayer.viewStates.PropertyListViewState

@Composable
fun PropertyListView(
    listViewState: PropertyListViewState,
    onItemClicked: (data: PropertyListUIData) -> Unit
) {
    Surface {
        if (listViewState is PropertyListViewState.Loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(40.dp).testTag("Loader")
            ) {
                CircularProgressIndicator()
            }
        }
        if (listViewState is PropertyListViewState.Failure) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.testTag("Error")
            ) {
                Text(text = listViewState.message, modifier = Modifier.testTag("Error"))
            }
        }
        if (listViewState is PropertyListViewState.Success) {
            val data = listViewState.items
            LazyColumn(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp).testTag("data"),
                verticalArrangement = Arrangement.spacedBy(22.dp),
            ) {
                items(
                    count = data.size,
                ) {
                    PropertyItemLayout(data = data[it], onItemClicked)
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPropertyListView() {
    val data = listOf(
        PropertyListUIData(
            id = 1,
            image = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
            price = "150000.0",
            city = "Villers-sur-Mer",
            rooms = "8",
            bedrooms = "4",
            area = "250.0"
        ),
        PropertyListUIData(
            id = 1,
            image = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
            price = "150000.0",
            city = "Villers-sur-Mer",
            rooms = "8",
            bedrooms = "4",
            area = "250.0"
        )
    )
    PropertyListView(PropertyListViewState.Success(data)) {}
}

@Preview
@Composable
private fun PreviewPropertyListViewFailure() {
    PropertyListView(PropertyListViewState.Failure("Something went wrong")) {}
}

@Preview
@Composable
private fun PreviewPropertyListViewLoading() {
    PropertyListView(PropertyListViewState.Loading) {}
}