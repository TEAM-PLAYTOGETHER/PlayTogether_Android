package com.playtogether_android.app.util

import android.provider.Settings.Global
import com.playtogether_android.app.BuildConfig
import com.playtogether_android.data.model.response.sign.ResTokenIssuance
import com.playtogether_android.data.singleton.PlayTogetherRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import timber.log.Timber

interface TokenRefreshService {
    @GET("auth/refresh")
    suspend fun getTokenIssuance(
        @Header("refresh") refresh: String = PlayTogetherRepository.userRefreshToken
    ): ResTokenIssuance
}

interface TokenRefreshSource {
    suspend fun refreshToken(): ResTokenIssuance
}

class TokenRefreshSourceImpl(
    private val service: TokenRefreshService
) : TokenRefreshSource {
    override suspend fun refreshToken(): ResTokenIssuance {
        return service.getTokenIssuance()
    }
}

class TokenManager {
    private lateinit var service: TokenRefreshService

    @OptIn(DelicateCoroutinesApi::class)
    fun refresh(): Int {
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(TokenRefreshService::class.java)
        var statusCode = -1
        GlobalScope.launch {
            kotlin.runCatching {
                service.getTokenIssuance(PlayTogetherRepository.userRefreshToken)
            }.onSuccess {
                statusCode = 200
                PlayTogetherRepository.userToken = it.data.accessToken
                Timber.d("token manager success")
            }.onFailure {
                Timber.e("token manager error $it")
            }
        }
        return statusCode
    }
}