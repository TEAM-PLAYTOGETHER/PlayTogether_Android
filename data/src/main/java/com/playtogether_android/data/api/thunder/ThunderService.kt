package com.playtogether_android.data.api.thunder

import com.playtogether_android.data.model.response.thunder.ResThunderTabListData
import com.playtogether_android.data.model.response.thunder.ResponseThunderJoinCancel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ThunderService {
    //번개탭-신청한 번개 리스트
    @GET("light/enter")
    suspend fun getApplyList(): ResThunderTabListData

    @POST("light/enter/{thunderId}")
    suspend fun postThunderJoinCancel(
        @Path("thunderId") thunderId: String
    ): ResponseThunderJoinCancel
}