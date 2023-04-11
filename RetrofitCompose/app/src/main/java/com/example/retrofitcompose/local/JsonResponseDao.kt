package com.example.retrofitcompose.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface JsonResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJsonResponse(jsonResponse: List<JsonResponse>)

    @Query("DELETE FROM json_response")
    suspend fun deleteJsonResponse()

    @Query("SELECT * FROM json_response")
    fun getJsonResponse(): Flow<List<JsonResponse>>
}