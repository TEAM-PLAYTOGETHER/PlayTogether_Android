package com.playtogether_android.data.datasource.sign

import com.playtogether_android.data.api.sign.SignService
import com.playtogether_android.data.model.request.sign.RequestSignId
import com.playtogether_android.data.model.response.sign.ResponseSignId

class SignDataSourceImpl(private val service: SignService): SignDataSource {
    override suspend fun postSignId(requestSignId: RequestSignId): ResponseSignId {
        return service.postSignId(requestSignId)
    }
}