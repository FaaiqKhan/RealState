package com.example.realstate.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PropertyDetailView() {
    Column {
        AsyncImage(
            model = "",
            contentDescription = "Property image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "15000.0",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Villers-sur-Mer",
                    color = Color.Gray
                )
            }
            Text(
                text = "Offer type: 1",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "House information",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            HouseDetailsCard(title = "250", subtitle = "meter square")
            HouseDetailsCard(title = "4", subtitle = "bedrooms")
            HouseDetailsCard(title = "8", subtitle = "rooms")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "City: Villers-sur-Mer",
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "price: 1500000.0",
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "professional: GSL EXPLORE",
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "propertyType: Maison - Villa",
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun HouseDetailsCard(title: String, subtitle: String) {
    val roundedBorderValue = 16.dp
    Card(
        modifier = Modifier
            .size(100.dp)
            .clip(shape = RoundedCornerShape(roundedBorderValue))
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(roundedBorderValue)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = subtitle,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
private fun PreviewPropertyDetailView() {
    PropertyDetailView()
}