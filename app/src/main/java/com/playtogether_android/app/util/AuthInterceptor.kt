package com.playtogether_android.app.util

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import com.playtogether_android.app.di.PlayTogetherApplication
import com.playtogether_android.app.presentation.ui.sign.SignInActivity
import com.playtogether_android.data.mapper.sign.SignMapper
import com.playtogether_android.data.model.response.sign.ResponseSignIn
import com.playtogether_android.domain.model.sign.SignInData
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import timber.log.Timber
import java.lang.Exception

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
        Log.d("response", ""+response)
        Log.d("response header", ""+response.headers)

        val appContext = PlayTogetherApplication.context()
        val data: SignInData? = chain.postRenewalData(appContext)       // access token 재발급
        val newToken = data!!.jwtToken
        PlayTogetherSharedPreference.setJwtToken(
            appContext,
            data!!.jwtToken
        )     // 재발급 access token 저장
        Log.d("token renewal",""+ newToken)
//        val newRequest = request.newBuilder()
//            .header("Authorization", newToken)
//            .build()
//        return chain.proceed(newRequest)

//        when (response.code) {
//
//            // 토큰 만료
//            401 -> {
//
//                response.close()
//
//                val appContext = PlayTogetherApplication.context()
//
//                if (PlayTogetherSharedPreference.getJwtToken(appContext)
//                        .isEmpty()
//                )     // refresh token 없으면 재발급 로직 실행 x -> 루프 방지
//                    return response
//
//                val data: SignInData? = chain.postRenewalData(appContext)       // access token 재발급
//
//                if (data != null && data.success) {      // access token 재발급 성공
//                    val newToken = data.jwtToken
//                    PlayTogetherSharedPreference.setJwtToken(
//                        appContext,
//                        data.jwtToken
//                    )     // 재발급 access token 저장
//                    Log.d("refresh token renewal",""+ newToken)
//                    val newRequest = request.newBuilder()
//                        .header("Authorization", newToken)
//                        .build()
//                    return chain.proceed(newRequest)
//                } else {
//                    Timber.d("refresh renewal failed")
//                    PlayTogetherSharedPreference.removeJwtToken(appContext)        // 만료된 access token 제거
//
//
//                    appContext.run {
//                        if (!isServiceRunning(SignInActivity::class.java.name))     // Splash 액티비티 실행 여부 확인
//                            restartApp()                                              // 재발급 실패 -> 앱 재실행 후 로그인
//                    }
//
//
//                }
//
//            }
//
//        }

        return response
    }

    private fun Response.extractRenewalData(): SignInData {
        try {
            val result = Gson().fromJson(body?.string(), ResponseSignIn::class.java)
            this.close()

            return SignMapper.mapperToSignInData(result)
        } catch (e: Exception) {
            throw e
        }
    }

    private fun Interceptor.Chain.postRenewalData(context: Context): SignInData? {
        val newRequest = Request.Builder()
            .url("${baseUrl}auth/login")
            .method("POST", "".toRequestBody())
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", PlayTogetherSharedPreference.getJwtToken(context))
            .build()

        return try {
            this.proceed(newRequest).extractRenewalData()
        } catch (e: Exception) {
            null
        }
    }

    fun Context.restartApp() {
        val intent = this.packageManager.getLaunchIntentForPackage(packageName)
        val mainIntent = Intent.makeRestartActivityTask(intent?.component)
        startActivity(mainIntent)
        Runtime.getRuntime().exit(0)

        Timber.d("ManageUtil: Application Restart")
    }

    fun Context.isServiceRunning(className: String): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val activityName = manager.appTasks[0].taskInfo.topActivity?.className

        return className == activityName
    }

}


