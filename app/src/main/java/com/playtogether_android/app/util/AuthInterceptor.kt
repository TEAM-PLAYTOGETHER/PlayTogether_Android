package com.playtogether_android.app.util

import com.playtogether_android.app.testdi.PlayTogetherApplication
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class AuthInterceptor : Interceptor {

    companion object {
        const val TAG = "okhttp"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val response = this.doRequest(chain)
        return response
        //return chain.proceed(request.build())
        Timber.d("request : $request")


        //Timber.d("request header : ${request.headers}")

        //val response = chain.proceed(request)
//        Timber.d("response : $response")
//        Timber.d("response header: ${response.headers}")
//        Log.d("response", "" + response)
//        Log.d("response header", "" + response.headers)
//        return response
    }

    private fun doRequest(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val cookieSid = PlayTogetherSharedPreference.getJwtToken(PlayTogetherApplication.context())
        if (cookieSid != null) {
            return chain.proceed(
                request
                    .newBuilder()
                    .addHeader(
                        "Authorization",
                        PlayTogetherSharedPreference.getJwtToken(PlayTogetherApplication.context())
                    )
                    .addHeader("Content-Type", "application/json")
                    .build()
            )
        }
        return chain.proceed(request)
    }
}


