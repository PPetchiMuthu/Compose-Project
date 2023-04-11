package com.example.retrofitcompose.di

import android.app.Application
import androidx.room.Room
import com.example.retrofitcompose.local.JsonResponseDatabase
import com.example.retrofitcompose.remote.JsonResponseApi
import com.example.retrofitcompose.repository.JsonResponseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JsonResponseModule {

    @Provides
    @Singleton
    fun provideJsonResponseRepository(
        api: JsonResponseApi,
        database: JsonResponseDatabase
    ): JsonResponseRepository {
        return JsonResponseRepository(api, database.jsonResponseDao)
    }

    @Provides
    @Singleton
    fun provideJsonResponseDatabase(app: Application): JsonResponseDatabase {
        return Room.databaseBuilder(
            app,
            JsonResponseDatabase::class.java,
            "json_response_db"
        ).build()
    }

    fun provideJsonResponseApi(): JsonResponseApi {
        return Retrofit.Builder()
            .baseUrl(JsonResponseApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JsonResponseApi::class.java)
    }
}