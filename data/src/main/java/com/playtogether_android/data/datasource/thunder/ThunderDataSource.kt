package com.playtogether_android.data.datasource.thunder

import com.playtogether_android.data.model.response.thunder.ResThunderTabListData
import com.playtogether_android.data.model.response.thunder.ResponseThunderJoinCancel

interface ThunderDataSource {

    //번개탭-신청한 번개 리스트
    suspend fun getApplyList(): ResThunderTabListData

    suspend fun postThunderJoinCancel(thunderId: String): ResponseThunderJoinCancel
}