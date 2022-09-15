package com.playtogether_android.data.api.thunder

import com.playtogether_android.data.model.response.thunder.*
import com.playtogether_android.data.singleton.PlayTogetherRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

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

    //번개 찜 확인
    @GET("scrap/exist/{lightId}")
    suspend fun getThunderScrap(
        @Path("lightId") lightId: Int
    ): ResThunderScrapData

    //번개 찜/취소
    @POST("scrap/{lightId}")
    suspend fun postScrap(
        @Path("lightId") lightId: Int
    )

    @POST("light/report/{lightId}")
    suspend fun postReport(
        @Path("lightId") lightId: Int
    )

    @Multipart
    @PUT("light/enter/{lightId}")
    suspend fun putThunder(
        @Path("lightId") lightId: Int,
        @Part image: MultipartBody.Part?,
        @PartMap body: HashMap<String, RequestBody>
    ): ResThunderChangeData

    @GET("light/exist/{lightId}")
    suspend fun getThunderExistChecker(
        @Path("lightId") lightId: Int,
    ):ResThunderExistCheckData
}