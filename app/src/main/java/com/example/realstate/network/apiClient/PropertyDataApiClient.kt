package com.example.realstate.network.apiClient

import com.example.realstate.data.PropertyListResponseData
import com.example.realstate.data.PropertyResponseData
import retrofit2.http.GET
import retrofit2.http.Path

interface PropertyDataApiClient {
    @GET("listings.json")
    suspend fun fetchProperties(): PropertyListResponseData

    @GET("listings{id}.json")
    suspend fun fetchPropertyDetails(@Path("id") id: Int): PropertyResponseData
}