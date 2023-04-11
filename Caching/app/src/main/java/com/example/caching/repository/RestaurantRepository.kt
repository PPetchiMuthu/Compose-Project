package com.example.caching.repository


import com.example.caching.data.local.RestaurantDao
import com.example.caching.data.local.Restaurant
import com.example.caching.data.remote.RestaurantApi
import com.example.caching.data.remote.RestaurantDto
import com.example.caching.util.Resource
import com.example.caching.util.networkBoundResource
import kotlinx.coroutines.flow.Flow

class RestaurantRepository(
    private val api: RestaurantApi,
    private val dao: RestaurantDao
) {
    fun getAllRestaurant(): Flow<Resource<List<Restaurant>>> = networkBoundResource(
        query = {
            dao.getAllRestaurants()
        },
        fetch = {
            api.getRestaurant()
        },
        saveFetchResult = { restaurants: List<RestaurantDto> ->
            dao.deleteAllRestaurants()
            dao.insertRestaurants(restaurants.map { it.restaurant() })
        }
    )
}