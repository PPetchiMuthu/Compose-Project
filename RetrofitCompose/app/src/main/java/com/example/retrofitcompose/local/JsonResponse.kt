package com.example.retrofitcompose.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "json_response")
data class JsonResponse(
    @PrimaryKey val id: Int,
    val userId: Int,
    val body: String,
    val title: String
)