package com.playtogether_android.data.datasource.thunder

import com.playtogether_android.data.model.response.thunder.ResThunderTabListData

interface ThunderDataSource {

    //번개탭-신청한 번개 리스트
    suspend fun getApplyList() : ResThunderTabListData
}