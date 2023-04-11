package com.example.caching.di

import android.app.Application
import androidx.room.Room
import com.example.caching.data.local.RestaurantDatabase
import com.example.caching.data.remote.RestaurantApi
import com.example.caching.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RestaurantModule {

    @Provides
    @Singleton
    fun providesRestaurantRepository(
        database: RestaurantDatabase,
        api: RestaurantApi
    ): RestaurantRepository {
        return RestaurantRepository(api, database.restaurantDao)
    }

    @Provides
    @Singleton
    fun provideRestaurantDatabase(app: Application): RestaurantDatabase {
        return Room.databaseBuilder(
            app,
            RestaurantDatabase::class.java,
            "restaurant_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesRestaurantApi(): RestaurantApi {
        return Retrofit.Builder()
            .baseUrl(RestaurantApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestaurantApi::class.java)
    }

}

