package com.playtogether_android.app.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.Response

class RefreshTokenInterceptor(
    private val coroutineScope: CoroutineScope
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val tokenManager = TokenManager()
        tokenManager.initService()
        if (response.code == 401) {
            response.close()
            val newAccessToken = coroutineScope.launch {
                tokenManager.getRefreshToken(coroutineScope).join()
            }
            val newRequest = request.newBuilder()
                .header("Authorization", "Bearer $newAccessToken")
                .build()
            return chain.proceed(newRequest)
        }
        return response
    }
}