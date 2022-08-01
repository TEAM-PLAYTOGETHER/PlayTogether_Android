package com.playtogether_android.data.api.onboarding

import com.playtogether_android.data.model.response.onboarding.ResponseSubwayList
import retrofit2.http.GET
import retrofit2.http.Path

interface SubwayInfoService {
    @GET("/{key}/{type}/{service}/{start_index}/{end_index}")
    suspend fun getSubwayInfo(
        @Path("key") key : String = "734e796a726c68623130356f4d4b7a43",
        @Path("type") type : String = "json",
        @Path("service") service : String = "SearchSTNBySubwayLineInfo",
        @Path("start_index") start_index : Int = 1,
        @Path("end_index") end_inedx : Int = 749,
    ) : ResponseSubwayList

}