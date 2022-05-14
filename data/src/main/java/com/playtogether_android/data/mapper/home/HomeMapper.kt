package com.playtogether_android.data.mapper.home

import com.playtogether_android.data.model.response.home.ResponseJoinThunder
import com.playtogether_android.data.model.response.home.ResponseThunderJoinEnd
import com.playtogether_android.data.model.response.mypage.ResponseUserCheck
import com.playtogether_android.data.model.response.thunder.ResThunderDetailData
import com.playtogether_android.domain.model.home.JoinThunderData
import com.playtogether_android.domain.model.home.ThunderJoinEndData
import com.playtogether_android.domain.model.home.ThunderJoinEndMember
import com.playtogether_android.domain.model.home.ThunderJoinEndOrganizer
import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.model.thunder.Member
import com.playtogether_android.domain.model.thunder.Organizer
import com.playtogether_android.domain.model.thunder.ThunderDetailData

object HomeMapper {
    fun mapperToJoinThunder(responseJoinThunder: ResponseJoinThunder): JoinThunderData {
        return JoinThunderData(
            success = responseJoinThunder.success
        )
    }

    //번개 신청 완료
    fun mapperToThunderJoinEndMember(it: List<ResponseThunderJoinEnd.Data>): List<ThunderJoinEndMember> {
        val list = mutableListOf<ThunderJoinEndMember>()
        it.map {
            list.addAll(it.members.map { ThunderJoinEndMember(it.age, it.gender, it.mbti, it.name, it.user_id) })
        }
        return list
    }

    fun mapperToThunderJoinEndOrganizer(it: List<ResponseThunderJoinEnd.Data>): List<ThunderJoinEndOrganizer> {
        val list = mutableListOf<ThunderJoinEndOrganizer>()
        it.map {
            list.addAll(it.organizer.map { ThunderJoinEndOrganizer(it.name, it.organizer_id) })
        }
        return list
    }

    fun mapperToThunderJoinEnd(it: ResponseThunderJoinEnd): List<ThunderJoinEndData> {
        return it.data.map {
            ThunderJoinEndData(
                it.LightMemberCnt,
                it.category,
                it.date,
                it.description,
                it.light_id,
                it.people_cnt,
                it.place,
                it.time,
                it.title
            )
        }
    }

}
