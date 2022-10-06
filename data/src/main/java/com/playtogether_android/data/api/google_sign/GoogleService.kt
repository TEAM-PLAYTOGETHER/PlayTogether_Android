package com.playtogether_android.data.api.google_sign

import com.google.gson.GsonBuilder
import com.playtogether_android.data.model.request.google.LoginGoogleRequestModel
import com.playtogether_android.data.model.request.google.LoginGoogleResponseModel
import com.playtogether_android.data.model.request.google.SendAccessTokenModel
import com.playtogether_android.data.model.response.sign.ResLoginGoogle
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GoogleService {
//    @POST("oauth2/v4/token")
//    fun getAccessToken(
//        @Body request: LoginGoogleRequestModel
//    ): Call<LoginGoogleResponseModel>

    @POST("oauth2/v4/token")
    suspend fun getAccessToken(
        @Body request: LoginGoogleRequestModel
    ): LoginGoogleResponseModel
//
//    companion object {
//        private val gson = GsonBuilder().setLenient().create()
//
//        fun loginRetrofit(baseUrl: String): GoogleService {
//            return Retrofit.Builder()
//                .baseUrl(baseUrl)
////                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build()
//                .create(GoogleService::class.java)
//        }
//    }
}