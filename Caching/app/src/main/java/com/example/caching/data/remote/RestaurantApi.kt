package com.example.caching.data.remote

import retrofit2.http.GET

interface RestaurantApi {

    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurant() : List<RestaurantDto>

    companion object {
        const val BASE_URL = "https://random-data-api.com/api/"
    }
}