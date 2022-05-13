package com.playtogether_android.data.datasource.thunder

import com.playtogether_android.data.api.thunder.ThunderService
import com.playtogether_android.data.model.response.thunder.ResThunderTabListData
import com.playtogether_android.data.model.response.thunder.ResponseThunderJoinCancel

class ThunderDataSourceImpl(private val service: ThunderService) : ThunderDataSource {

    //번개탭-신청한 번개 리스트
    override suspend fun getApplyList(): ResThunderTabListData {
        return service.getApplyList()
    }

    //번개탭-오픈한 번개 리스트
    override suspend fun getOpenList(): ResThunderTabListData {
        return service.getOpenList()
    }

    //번개탭-찜한 번개 리스트
    override suspend fun getLikeList(): ResThunderTabListData {
        return service.getLikeList()
    }

    override suspend fun postThunderJoinCancel(thunderId: Int): ResponseThunderJoinCancel {
        return service.postThunderJoinCancel(thunderId)
    }
}