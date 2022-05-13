package com.playtogether_android.data.datasource.mypage

import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.request.sign.RequestSignIn
import com.playtogether_android.data.model.request.sign.RequestSignUp
import com.playtogether_android.data.model.response.mypage.ResponseUserCheck
import com.playtogether_android.data.model.response.sign.ResponseSignId
import com.playtogether_android.data.model.response.sign.ResponseSignIn
import com.playtogether_android.data.model.response.sign.ResponseSignUp

interface MyPageDataSource {

    //유저 조회
    suspend fun getUserCheck(userLoginId: String):ResponseUserCheck

}