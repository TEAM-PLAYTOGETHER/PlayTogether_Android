package com.playtogether_android.domain.repository.thunder

import com.playtogether_android.domain.model.GenericData
import com.playtogether_android.domain.model.thunder.*

interface ThunderRepository {

    //번개탭-신청한 번개 리스트
    suspend fun getApplyList(): ThunderTabListData

    //번개탭-오픈한 번개 리스트
    suspend fun getOpenList(): ThunderTabListData

    //번개탭-찜한 번개 리스트
    suspend fun getLikeList(): ThunderTabListData

    suspend fun postThunderJoinCancel(thunderId: Int): ThunderJoinCancelData

    //    번개 상세 뷰
    suspend fun getThunderDetail(thunderId: Int): List<ThunderDetailData>

    suspend fun getThunderDetailMember(thunderId: Int): List<Member>

    suspend fun getThunderDetailOrganizer(thunderId: Int): List<Organizer>

    //번개 삭제
    suspend fun postThunderDelete(thunderId: Int): ThunderDeleteData

    //번개 찜여부 조회
    suspend fun getThunderScrap(thunderId: Int): Boolean

    //번개 찜 / 취소
    suspend fun postScrap(thunderId: Int)

    //번개 게시글 신고
    suspend fun postReport(thunderId: Int, reportData: ReportData): GenericData

    //번개 신청 및 오픈 여부 확인
    suspend fun getThunderExistCheck(thunderId: Int): GetThunderExistCheck
}