package com.playtogether_android.data.api.thunder

import com.playtogether_android.data.model.response.thunder.ResThunderTabListData
import retrofit2.http.GET

interface ThunderService {
    //번개탭-신청한 번개 리스트
    @GET("light/enter")
    suspend fun getApplyList() : ResThunderTabListData

    //번개탭-오픈한 번개 리스트
    @GET("light/open")
    suspend fun getOpenList() : ResThunderTabListData

    //번개탭-찜한 번개 리스트
    @GET("light/scrap")
    suspend fun getLikeList() : ResThunderTabListData
}