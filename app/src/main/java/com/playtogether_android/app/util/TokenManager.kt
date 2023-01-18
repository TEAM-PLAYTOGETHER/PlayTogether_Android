package com.playtogether_android.app.util

import com.playtogether_android.app.BuildConfig
import com.playtogether_android.data.model.response.sign.ResTokenIssuance
import com.playtogether_android.data.singleton.PlayTogetherRepository
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import timber.log.Timber

class TokenManager {
    private lateinit var service: TokenRefreshService

    init {
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(TokenRefreshService::class.java)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getRefreshToken() = GlobalScope.launch {
        kotlin.runCatching {
            service.getTokenIssuance(
                PlayTogetherRepository.userToken, PlayTogetherRepository.userRefreshToken
            )
        }.onSuccess {
            PlayTogetherRepository.userToken = it.data.accessToken
            PlayTogetherRepository.userRefreshToken = it.data.refreshToken
            Timber.d("token manager success")
        }.onFailure {
            Timber.e("token access :${PlayTogetherRepository.userToken}")
            Timber.e("token access :${PlayTogetherRepository.userRefreshToken}")
            Timber.e("token manager error $it")
        }
    }
}