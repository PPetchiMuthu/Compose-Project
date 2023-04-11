package com.example.retrofitcompose.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JsonResponse::class], version = 1)
abstract class JsonResponseDatabase : RoomDatabase() {

    abstract val jsonResponseDao: JsonResponseDao

}