package com.playtogether_android.data.api.sign

import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.request.sign.RequestSignUp
import com.playtogether_android.data.model.response.sign.ResponseSignId
import com.playtogether_android.data.model.response.sign.ResponseSignUp
import retrofit2.http.Body
import retrofit2.http.POST

interface SignService {
    //아이디 중복확인
    @POST("auth/usercheck")
    suspend fun postSignId(
        @Body requestSignId : RequestSignId
    ): ResponseSignId

    //회원가입
    @POST("auth/signup")
    suspend fun postSignUp(
        @Body requestSignUp: RequestSignUp
    ) : ResponseSignUp
}