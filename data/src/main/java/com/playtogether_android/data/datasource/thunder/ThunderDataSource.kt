package com.playtogether_android.data.datasource.thunder

import com.playtogether_android.data.model.response.thunder.ResThunderTabListData

interface ThunderDataSource {

    //번개탭-신청한 번개 리스트
    suspend fun getApplyList() : ResThunderTabListData

    //번개탭-오픈한 번개 리스트
    suspend fun getOpenList() : ResThunderTabListData

    //번개탭-찜한 번개 리스트
    suspend fun getLikeList() :ResThunderTabListData
}