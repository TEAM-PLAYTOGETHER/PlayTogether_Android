package com.playtogether_android.app.util

import com.playtogether_android.app.di.PlayTogetherApplication
import com.playtogether_android.data.singleton.PlayTogetherRepository
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
        Timber.d("request : $request")
        val token = PlayTogetherRepository.userToken
//        val cookieSid = PlayTogetherSharedPreference.getJwtToken(PlayTogetherApplication.context())
        if (token.isNotEmpty()) {
            return chain.proceed(
                request
                    .newBuilder()
                    .addHeader(
                        "Authorization",
                        token
//                        PlayTogetherSharedPreference.getJwtToken(PlayTogetherApplication.context())
                    )
                    .addHeader("Content-Type", "application/json")
                    .build()
            )
        }
        return chain.proceed(request)
    }


    /*
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

        when (response.code) {

            // 토큰 만료
            401 -> {

                response.close()

                val appContext = PlayTogetherApplication.context()

                if (PlayTogetherSharedPreference.getRefreshToken(appContext).isEmpty())     // refresh token 없으면 재발급 로직 실행 x -> 루프 방지
                    return response

                val data: SocialLoginData? = chain.postRenewalData(appContext)       // access token 재발급

                if (data != null) {      // access token 재발급 성공
                    val newToken = data.accessToken
                    PlayTogetherSharedPreference.setAccessToken(appContext, data.accessToken)     // 재발급 access token 저장
                    Timber.d("refresh token renewal : ${newToken}")
                    val newRequest = request.newBuilder()
                        .header("accesstoken", newToken)
                        .build()
                    return chain.proceed(newRequest)
                }
                else {
                    Timber.d("refresh renewal failed")
                    PlayTogetherSharedPreference.removeAccessToken(appContext)        // 만료된 access token 제거
                    PlayTogetherSharedPreference.removeRefreshToken(appContext)      // 만료된 refresh token 제거

                    appContext.run {
                        if (!isServiceRunning(SplashActivity::class.java.name))     // Splash 액티비티 실행 여부 확인
                            restartApp()                                              // 재발급 실패 -> 앱 재실행 후 로그인
                    }
                }

            }

        }

        return response
    }

    private fun Response.extractRenewalData(): SocialLoginData {
        try {
            val result = Gson().fromJson(body?.string(), ResSocialLogin::class.java)
            this.close()

            return SignMapper.mapperToSocialData(result)
        } catch (e: Exception) {
            throw e
        }
    }

    private fun Interceptor.Chain.postRenewalData(context: Context): SocialLoginData? {
        val newRequest = Request.Builder()
            .url("${BASE_URL}/auth/refresh")
            .method("POST", "".toRequestBody())
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", PlayTogetherSharedPreference.getRefreshToken(context))
            .build()

        return try {
            this.proceed(newRequest).extractRenewalData()
        } catch (e: Exception) {
            null
        }
    }

*/


}


