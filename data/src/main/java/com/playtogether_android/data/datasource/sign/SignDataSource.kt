package com.playtogether_android.data.datasource.sign

import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.response.sign.ResponseSignId

interface SignDataSource {

    //아이디 중복확인
    suspend fun postSignId(requestSignId: RequestSignId) : ResponseSignId
}