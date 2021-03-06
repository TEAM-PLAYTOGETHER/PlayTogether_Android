package com.playtogether_android.data.datasource.thunder

import com.playtogether_android.data.model.response.thunder.ResThunderDeleteData
import com.playtogether_android.data.model.response.thunder.ResThunderDetailData
import com.playtogether_android.data.model.response.thunder.ResThunderTabListData
import com.playtogether_android.data.model.response.thunder.ResponseThunderJoinCancel

interface ThunderDataSource {

    //번개탭-신청한 번개 리스트
    suspend fun getApplyList(): ResThunderTabListData

    //번개탭-오픈한 번개 리스트
    suspend fun getOpenList(): ResThunderTabListData

    //번개탭-찜한 번개 리스트
    suspend fun getLikeList(): ResThunderTabListData

    suspend fun postThunderJoinCancel(thunderId: Int): ResponseThunderJoinCancel

    //번개 상세 뷰
    suspend fun getThunderDetail(thunderId: Int): ResThunderDetailData

    //번개 삭제
    suspend fun postThunderDelete(thunderId: Int): ResThunderDeleteData
}