package com.example.caching.ui.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caching.data.local.Restaurant
import com.example.caching.repository.RestaurantRepository
import com.example.caching.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {

    private val _restaurantList =
        MutableStateFlow<Resource<List<Restaurant>>>(Resource.Loading())
    val restaurantList: StateFlow<Resource<List<Restaurant>>> = _restaurantList


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllRestaurant()
                .collect { resource ->
                    when (resource) {
                        is Resource.Error -> _restaurantList.value = resource
                        is Resource.Loading -> _restaurantList.value = resource
                        is Resource.Success -> _restaurantList.value = resource
                    }
                }
        }
    }
}