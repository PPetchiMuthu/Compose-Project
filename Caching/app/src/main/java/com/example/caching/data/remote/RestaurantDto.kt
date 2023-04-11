package com.example.caching.data.remote

import com.example.caching.data.local.Restaurant

data class RestaurantDto(
    val name: String,
    val type: String,
    val logo: String,
    val address: String
) {
    fun restaurant(): Restaurant {
        return Restaurant(
            name = name,
            type = type,
            logo = logo,
            address = address
        )
    }
}