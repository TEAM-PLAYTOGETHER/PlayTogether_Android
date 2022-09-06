package com.playtogether_android.data.repositoryimpl.thunder

import com.playtogether_android.data.datasource.thunder.ThunderDataSource
import com.playtogether_android.data.mapper.thunder.ThunderMapper
import com.playtogether_android.domain.model.thunder.*
import com.playtogether_android.domain.repository.thunder.ThunderRepository

class ThunderRepositoryImpl(private val thunderDataSource: ThunderDataSource) : ThunderRepository {
    //번개탭-신청한 번개 리스트
    override suspend fun getApplyList(): ThunderTabListData {
        return ThunderMapper.mapperToThunderTabListData(thunderDataSource.getApplyList())
    }

    //번개탭-오픈한 번개 리스트
    override suspend fun getOpenList(): ThunderTabListData {
        return ThunderMapper.mapperToThunderTabListData(thunderDataSource.getOpenList())
    }

    //번개탭-찜한 번개 리스트
    override suspend fun getLikeList(): ThunderTabListData {
        return ThunderMapper.mapperToThunderTabListData(thunderDataSource.getLikeList())
    }

    override suspend fun postThunderJoinCancel(thunderId: Int): ThunderJoinCancelData {
        return ThunderMapper.mapperToThunderJoinCancel(
            thunderDataSource.postThunderJoinCancel(
                thunderId
            )
        )
    }

    override suspend fun getThunderDetail(thunderId: Int): List<ThunderDetailData> {
        return ThunderMapper.mapperToThunderDetail(thunderDataSource.getThunderDetail(thunderId))
    }

    override suspend fun getThunderDetailMember(thunderId: Int): List<Member> {
        val data = thunderDataSource.getThunderDetail(thunderId).data
        return ThunderMapper.mapperToThunderDetailMember(data)
    }

    override suspend fun getThunderDetailOrganizer(thunderId: Int): List<Organizer> {
        val data = thunderDataSource.getThunderDetail(thunderId).data
        return ThunderMapper.mapperToThunderDetailOrganizer(data)
    }

    override suspend fun postThunderDelete(thunderId: Int): ThunderDeleteData {
        return ThunderMapper.mapperToThunderDelete(
            thunderDataSource.postThunderDelete(
                thunderId
            )
        )
    }

    override suspend fun getThunderScrap(thunderId: Int): Boolean {
        return thunderDataSource.getThunderScrap(thunderId).data
    }

    override suspend fun postScrap(thunderId: Int) {
        thunderDataSource.postScrap(thunderId)
    }

    override suspend fun postReport(thunderId: Int) {
        thunderDataSource.postReport(thunderId)
    }
}
