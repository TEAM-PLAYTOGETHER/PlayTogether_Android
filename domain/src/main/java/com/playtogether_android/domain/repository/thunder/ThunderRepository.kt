package com.playtogether_android.domain.repository.thunder

import com.playtogether_android.domain.model.thunder.*

interface ThunderRepository {

    //번개탭-신청한 번개 리스트
    suspend fun getApplyList(): ThunderTabListData

    //번개탭-오픈한 번개 리스트
    suspend fun getOpenList(): ThunderTabListData

    //번개탭-찜한 번개 리스트
    suspend fun getLikeList(): ThunderTabListData

    suspend fun postThunderJoinCancel(thunderId: String): ThunderJoinCancelData

    //    번개 상세 뷰
    suspend fun getThunderDetail(thunderId: Int): List<ThunderDetailData>

    suspend fun getMemberThunderDetail(thunderId: Int): Member

    suspend fun getOrganizerThunderDetail(thunderId: Int): Organizer


}