package com.playtogether_android.data.mapper.thunder

import com.playtogether_android.data.model.response.thunder.ResThunderDetailData
import com.playtogether_android.data.model.response.thunder.ResThunderTabListData
import com.playtogether_android.data.model.response.thunder.ResponseThunderJoinCancel
import com.playtogether_android.domain.model.thunder.*

object ThunderMapper {

    //번개탭-신청한 번개 리스트
    fun mapperToThunderTabListData(resThunderTabListData: ResThunderTabListData): ThunderTabListData {
        return ThunderTabListData(
            data = resThunderTabListData.data.map {
                ThunderTabListData.Data(
                    it.lightId,
                    it.title,
                    it.date,
                    it.time,
                    it.peopleCnt,
                    it.place,
                    it.lightMemberCnt
                )
            }
        )
    }


    fun mapperToThunderJoinCancel(it: ResponseThunderJoinCancel): ThunderJoinCancelData {
        return ThunderJoinCancelData(
            status = it.status,
            success = it.success,
            message = it.message
        )
    }

    fun mapperToThunderDetail(it: ResThunderDetailData): List<ThunderDetailData> {
        return it.data.map {
            ThunderDetailData(
                it.category,
                it.date,
                it.description,
                it.image,
                it.lightId,
                it.lightMemberCnt,
                it.peopleCnt,
                it.place,
                it.time,
                it.title
            )
        }
    }

    fun mapperToMemberThunderDetail(it: ResThunderDetailData.Member): Member {
        return Member(
            it.age,
            it.gender,
            it.mbti,
            it.name,
            it.userId,
        )
    }

    fun mapperToOrganizerThunderDetail(it: ResThunderDetailData.Organizer): Organizer {
        return Organizer(
            it.name,
            it.organizerId,
        )
    }


}