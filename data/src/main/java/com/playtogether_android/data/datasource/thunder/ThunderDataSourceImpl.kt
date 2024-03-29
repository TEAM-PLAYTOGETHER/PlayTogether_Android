package com.playtogether_android.data.datasource.thunder

import com.playtogether_android.data.api.thunder.ThunderService
import com.playtogether_android.data.model.ResGenericData
import com.playtogether_android.data.model.request.thunder.ReqReportData
import com.playtogether_android.data.model.response.thunder.*

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

    override suspend fun getThunderDetail(thunderId: Int): ResThunderDetailData {
        return service.getThunderDetail(thunderId)
    }

    override suspend fun postThunderDelete(thunderId: Int): ResThunderDeleteData {
        return service.postThunderDelete(thunderId)
    }

    override suspend fun getThunderScrap(thunderId: Int): ResThunderScrapData {
        return service.getThunderScrap(thunderId)
    }

    override suspend fun postScrap(thunderId: Int) {
        service.postScrap(thunderId)
    }

    override suspend fun postReport(thunderId: Int, reqReportData: ReqReportData): ResGenericData {
        return service.postReport(thunderId, reqReportData)
    }

    override suspend fun getThunderExistChecker(thunderId: Int): ResThunderExistCheckData {
        return service.getThunderExistChecker(thunderId)
    }


}