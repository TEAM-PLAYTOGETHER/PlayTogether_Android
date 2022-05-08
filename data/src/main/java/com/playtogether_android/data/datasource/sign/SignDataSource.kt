package com.playtogether_android.data.datasource.sign

import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.request.sign.RequestSignUp
import com.playtogether_android.data.model.response.sign.ResponseSignId
import com.playtogether_android.data.model.response.sign.ResponseSignUp

interface SignDataSource {

    //아이디 중복확인
    suspend fun postSignId(requestSignId: RequestSignId) : ResponseSignId

    //회원가입
    suspend fun postSignUp(requestSignUp: RequestSignUp) : ResponseSignUp
}