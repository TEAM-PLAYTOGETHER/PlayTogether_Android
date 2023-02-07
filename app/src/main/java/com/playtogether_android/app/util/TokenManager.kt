package com.playtogether_android.app.util

import com.playtogether_android.app.BuildConfig
import com.playtogether_android.data.singleton.PlayTogetherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class TokenManager {
    private lateinit var service: TokenRefreshService

    fun initService() {
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(TokenRefreshService::class.java)
    }

    fun getRefreshToken(coroutineScope: CoroutineScope) = coroutineScope.launch {
        val result = runCatching {
            service.getTokenIssuance(
                PlayTogetherRepository.userToken, PlayTogetherRepository.userRefreshToken
            )
        }
        if (result.isSuccess) {
            result.getOrNull()?.let {
                PlayTogetherRepository.userToken = it.data.accessToken
                PlayTogetherRepository.userRefreshToken = it.data.refreshToken
                Timber.d("token manager success")
            } ?: run {
                Timber.e("token manager error: result is null")
            }
        } else {
            Timber.e("token manager error: ${result.exceptionOrNull()}")
        }
    }
}
