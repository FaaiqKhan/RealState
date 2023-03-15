package com.example.realstate.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realstate.data.PropertyListUIData
import com.example.realstate.viewStates.PropertyListViewState

@Composable
fun PropertyListView(
    listViewState: PropertyListViewState,
    onItemClicked: (data: PropertyListUIData) -> Unit
) {
    if (listViewState is PropertyListViewState.Loading) {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp)
        )
    }
    if (listViewState is PropertyListViewState.Failure) {
        Text(text = listViewState.message)
    }
    if (listViewState is PropertyListViewState.Success) {
        val data = listViewState.items
        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                count = data.size,
            ) {
                PropertyItemLayout(data = data[it], onItemClicked)
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
            price = 150000.0,
            city = "Villers-sur-Mer",
            rooms = 8,
            bedrooms = 4,
            area = 250.0
        ),
        PropertyListUIData(
            id = 1,
            image = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
            price = 150000.0,
            city = "Villers-sur-Mer",
            rooms = 8,
            bedrooms = 4,
            area = 250.0
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