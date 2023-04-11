package com.example.retrofitcompose.remote

import retrofit2.http.GET

interface JsonResponseApi {
    @GET("posts")
    suspend fun getJsonResponse(): List<JsonResponseDto>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}