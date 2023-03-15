package com.example.realstate.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PropertyListResponseData(
    val items: List<PropertyResponseData>
)

@JsonClass(generateAdapter = true)
data class PropertyResponseData(
    val id: Int,
    val city: String,
    val bedrooms: Int?,
    val area: Double,
    @Json(name = "url")
    val imageUrl: String?,
    val price: Double,
    val professional: String,
    val propertyType: String,
    val offerType: Int,
    val rooms: Int?,
)