package com.playtogether_android.data.datasource.thunder

import com.playtogether_android.data.model.response.thunder.*

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

    //번개 찜 여부 조히
    suspend fun getThunderScrap(thunderId: Int): ResThunderScrapData

    //번개 찜/취소
    suspend fun postScrap(thunderId: Int)

    //번개 신고
    suspend fun postReport(thunderId: Int)
}