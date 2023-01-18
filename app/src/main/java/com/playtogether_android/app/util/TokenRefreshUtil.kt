package com.playtogether_android.app.util

import com.playtogether_android.data.model.response.sign.ResTokenIssuance
import retrofit2.http.GET
import retrofit2.http.Header

interface TokenRefreshService {
    @GET("auth/refresh")
    suspend fun getTokenIssuance(
        @Header("Authorization") access: String, @Header("refresh") refresh: String
    ): ResTokenIssuance
}

interface TokenRefreshSource {
    suspend fun refreshToken(access: String, refresh: String): ResTokenIssuance
}

class TokenRefreshSourceImpl(
    private val service: TokenRefreshService
) : TokenRefreshSource {
    override suspend fun refreshToken(access: String, refresh: String): ResTokenIssuance {
        return service.getTokenIssuance(access, refresh)
    }
}