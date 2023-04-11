package com.example.retrofitcompose.ui.main_screen

import androidx.lifecycle.ViewModel
import com.example.retrofitcompose.local.JsonResponse
import com.example.retrofitcompose.repository.JsonResponseRepository
import com.example.retrofitcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: JsonResponseRepository
) : ViewModel() {

    private val _jsonResponseList =
        MutableStateFlow<Resource<List<JsonResponse>>>(Resource.Loading())
    val jsonResponseList: StateFlow<Resource<List<JsonResponse>>> = _jsonResponseList

}