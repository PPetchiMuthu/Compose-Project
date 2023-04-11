package com.example.retrofitcompose.repository

import com.example.retrofitcompose.local.JsonResponseDao
import com.example.retrofitcompose.remote.JsonResponseApi
import com.example.retrofitcompose.util.networkBoundResource

class JsonResponseRepository(
    private val api: JsonResponseApi,
    private val dao: JsonResponseDao
) {
    fun getJsonResponse() = networkBoundResource(
        query = {
            dao.getJsonResponse()
        },
        fetch = {
            api.getJsonResponse()
        },
        saveFetchResult = { jsonResponse ->
            dao.deleteJsonResponse()
            dao.insertJsonResponse(jsonResponse.map { it.toJsonResponse() })
        }
    )
}