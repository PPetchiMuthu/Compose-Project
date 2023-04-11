package com.example.caching.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class Restaurant(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val type: String,
    val logo: String,
    val address: String
)