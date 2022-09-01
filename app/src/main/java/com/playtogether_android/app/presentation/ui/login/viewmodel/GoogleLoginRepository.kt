package com.playtogether_android.app.presentation.ui.login.viewmodel

import android.util.Log
import com.playtogether_android.data.api.google_sign.GoogleRepository
import com.playtogether_android.data.api.google_sign.GoogleService
import com.playtogether_android.data.model.request.google.LoginGoogleRequestModel
import com.playtogether_android.data.model.request.google.LoginGoogleResponseModel
import com.playtogether_android.data.singleton.PlayTogetherRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoogleLoginRepository(
    private val clientId: String,
    private val clientSecret: String
) {

    fun getAccessToken(authCode: String) {
        GoogleService.loginRetrofit(BASE_URL).getAccessToken(
            request = LoginGoogleRequestModel(
                grant_type = "authorization_code",
                client_id = clientId,
                client_secret = clientSecret,
                code = authCode.orEmpty()
            )
        ).enqueue(object : Callback<LoginGoogleResponseModel> {
            override fun onResponse(
                call: Call<LoginGoogleResponseModel>,
                response: Response<LoginGoogleResponseModel>
            ) {
                if (response.isSuccessful) {
                    val accessToken = response.body()?.access_token.orEmpty()
                    PlayTogetherRepository.googleAccessToken = accessToken
                }
            }

            override fun onFailure(call: Call<LoginGoogleResponseModel>, t: Throwable) {
                Log.e(GoogleRepository.TAG, "getOnFailure: ", t.fillInStackTrace())
            }
        })
    }

    companion object {
        const val BASE_URL = "https://www.googleapis.com"
    }

}