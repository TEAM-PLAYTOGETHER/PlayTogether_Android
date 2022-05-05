package com.playtogether_android.data.mapper.sign

import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.response.sign.ResponseSignId
import com.playtogether_android.domain.model.sign.IdDuplicationCheckData
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem

object SignMapper {

    //아이디 중복 확인 : Response
    fun mapperToIdDuplicationData(responseSignId: ResponseSignId) : IdDuplicationCheckData {
        return IdDuplicationCheckData(
            isUser = responseSignId.data.isUser
        )
    }

    //아이디 중복 확인 : Request
    fun mapperToIdDuplicationItem(idDuplicationCheckItem: IdDuplicationCheckItem) : RequestSignId {
        return RequestSignId(
            userLoginId = idDuplicationCheckItem.userLoginId
        )
    }
}