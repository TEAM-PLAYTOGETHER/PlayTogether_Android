package com.playtogether_android.data.mapper.sign

import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.request.sign.RequestSignUp
import com.playtogether_android.data.model.response.sign.ResponseSignId
import com.playtogether_android.data.model.response.sign.ResponseSignUp
import com.playtogether_android.domain.model.sign.IdDuplicationCheckData
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem
import com.playtogether_android.domain.model.sign.SignUpData
import com.playtogether_android.domain.model.sign.SignUpItem

object SignMapper {

    //아이디 중복 확인 : Response
    fun mapperToIdDuplicationData(responseSignId: ResponseSignId): IdDuplicationCheckData {
        return IdDuplicationCheckData(
            isUser = responseSignId.data.isUser
        )
    }

    //아이디 중복 확인 : Request
    fun mapperToIdDuplicationItem(idDuplicationCheckItem: IdDuplicationCheckItem): RequestSignId {
        return RequestSignId(
            userLoginId = idDuplicationCheckItem.userLoginId
        )
    }

    //회원가입 response
    fun mapperToSignupData(responseSignUp: ResponseSignUp): SignUpData {
        return SignUpData(
            success = responseSignUp.success
        )
    }

    //회원가입 request
    fun mapperToSignupItem(signUpItem: SignUpItem) : RequestSignUp {
        return RequestSignUp(
            userLoginId = signUpItem.userLoginId,
            password = signUpItem.password,
            userName = signUpItem.userName,
            gender = signUpItem.gender,
            birth = signUpItem.birth,
            mbti = signUpItem.mbti
        )
    }
}