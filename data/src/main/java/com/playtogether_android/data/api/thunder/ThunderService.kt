package com.playtogether_android.data.api.thunder

import com.playtogether_android.data.model.response.thunder.ResThunderDeleteData
import com.playtogether_android.data.model.response.thunder.ResThunderDetailData
import com.playtogether_android.data.model.response.thunder.ResThunderTabListData
import com.playtogether_android.data.model.response.thunder.ResponseThunderJoinCancel
import com.playtogether_android.data.singleton.PlayTogetherRepository
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ThunderService {
    //번개탭-신청한 번개 리스트
    @GET("light/{crewId}/enter")
    suspend fun getApplyList(
        @Path("crewId") crewId: Int = PlayTogetherRepository.crewId
    ): ResThunderTabListData

    //번개탭-오픈한 번개 리스트
    @GET("light/{crewId}/open")
    suspend fun getOpenList(
        @Path("crewId") crewId: Int = PlayTogetherRepository.crewId
    ): ResThunderTabListData

    //번개탭-찜한 번개 리스트
    @GET("light/{crewId}/scrap")
    suspend fun getLikeList(
        @Path("crewId") crewId: Int = PlayTogetherRepository.crewId
    ): ResThunderTabListData

    @POST("light/enter/{thunderId}")
    suspend fun postThunderJoinCancel(
        @Path("thunderId") thunderId: Int
    ): ResponseThunderJoinCancel

    @GET("light/detail/{thunderId}")
    suspend fun getThunderDetail(
        @Path("thunderId") thunderId: Int,
    ): ResThunderDetailData

    //번개삭제
    @POST("light/remove/{thunderId}")
    suspend fun postThunderDelete(
        @Path("thunderId") thunderId: Int
    ): ResThunderDeleteData

}