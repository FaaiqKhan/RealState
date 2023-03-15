package com.example.realstate.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realstate.data.PropertyUIData

@Composable
fun PropertyListView() {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            count = 2
        ) {
            PropertyItemLayout(
                PropertyUIData(
                    id = 1,
                    image = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                    price = 150000.0,
                    city = "Villers-sur-Mer",
                    rooms = 8,
                    bedrooms = 4,
                    area = 250.0
                )
            )
        }
    }
}

@Preview
@Composable
private fun PreviewPropertyListView() {
    PropertyListView()
}