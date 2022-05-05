package com.playtogether_android.app.util

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class AuthInterceptor(
    private val baseUrl: String
) : Interceptor {

    companion object {
        const val TAG = "okhttp"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Timber.d("request : $request")
        Timber.d("request header : ${request.headers}")

        val response = chain.proceed(request)
        Timber.d("response : $response")
        Timber.d("response header: ${response.headers}")

        return response

    }
}
