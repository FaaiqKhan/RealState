package com.example.realstate.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.realstate.data.PropertyListUIData

@Composable
fun PropertyItemLayout(data: PropertyListUIData) {
    Surface {
        Column {
            AsyncImage(
                model = data.image,
                contentDescription = "Property image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(height = 14.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.price.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = data.city,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(height = 14.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${data.rooms} rooms/ ",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${data.bedrooms} bedrooms/ ",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${data.area} msq",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPropertyItemLayout() {
    PropertyItemLayout(
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
}