package com.example.caching.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants: List<Restaurant>)

    @Query(value = "DELETE FROM restaurant")
    suspend fun deleteAllRestaurants()

    @Query("SELECT * FROM restaurant")
    fun getAllRestaurants(): Flow<List<Restaurant>>
}