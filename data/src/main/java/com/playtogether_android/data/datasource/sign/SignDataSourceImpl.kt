package com.playtogether_android.data.datasource.sign

import com.playtogether_android.data.api.sign.SignService
import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.request.sign.RequestSignUp
import com.playtogether_android.data.model.response.sign.ResponseSignId
import com.playtogether_android.data.model.response.sign.ResponseSignUp

class SignDataSourceImpl(private val service: SignService): SignDataSource {
    //아이디 중복 확인
    override suspend fun postSignId(requestSignId: RequestSignId): ResponseSignId {
        return service.postSignId(requestSignId)
    }

    //회원가입
    override suspend fun postSignUp(requestSignUp: RequestSignUp): ResponseSignUp {
        return service.postSignUp(requestSignUp)
    }
}