package com.playtogether_android.data.datasource.thunder

import com.playtogether_android.data.api.thunder.ThunderService
import com.playtogether_android.data.model.response.thunder.ResThunderTabListData
import com.playtogether_android.data.model.response.thunder.ResponseThunderJoinCancel

class ThunderDataSourceImpl(private val service: ThunderService) : ThunderDataSource {

    override suspend fun getApplyList(): ResThunderTabListData {
        return service.getApplyList()
    }

    override suspend fun postThunderJoinCancel(thunderId: String): ResponseThunderJoinCancel {
        return service.postThunderJoinCancel(thunderId)
    }
}