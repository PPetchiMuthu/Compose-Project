package com.example.retrofitcompose.remote

import com.example.retrofitcompose.local.JsonResponse

data class JsonResponseDto(
    val id: Int,
    val userId: Int,
    val body: String,
    val title: String
) {
    fun toJsonResponse(): JsonResponse {
        return JsonResponse(
            id = id,
            userId = userId,
            body = body,
            title = title
        )
    }

}
