package com.example.realstate.network

import com.squareup.moshi.JsonClass

sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Failure(val error: ErrorResponse) : NetworkResponse<Nothing>()
}

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val statusCode: Int = 0,
    val errorCode: Int? = 0,
    val message: String?
)