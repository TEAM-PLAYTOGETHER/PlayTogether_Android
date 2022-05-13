package com.playtogether_android.data.mapper.home

import com.playtogether_android.data.model.response.home.ResponseJoinThunder
import com.playtogether_android.data.model.response.home.ResponseThunderJoinEnd
import com.playtogether_android.data.model.response.mypage.ResponseUserCheck
import com.playtogether_android.domain.model.home.JoinThunderData
import com.playtogether_android.domain.model.home.ThunderJoinEndData
import com.playtogether_android.domain.model.mypage.UserCheckData

object HomeMapper {
    fun mapperToJoinThunder(responseJoinThunder: ResponseJoinThunder): JoinThunderData {
        return JoinThunderData(
            success = responseJoinThunder.success
        )
    }

    //번개 신청 완료
    fun mapperToThunderJoinEnd(responseThunderJoinEnd: ResponseThunderJoinEnd): ThunderJoinEndData {
        return ThunderJoinEndData(
            data = responseThunderJoinEnd.data.map {
                ThunderJoinEndData.Data(
                    it.LightMemberCnt,
                    it.category,
                    it.date,
                    it.description,
                    it.light_id,
                    it.members.map {
                        ThunderJoinEndData.Data.Member(
                            it.age,
                            it.gender,
                            it.mbti,
                            it.name,
                            it.user_id
                        )
                    },
                    it.organizer.map {
                        ThunderJoinEndData.Data.Organizer(
                            it.name,
                            it.organizer_id
                        )
                    },
                    it.people_cnt,
                    it.place,
                    it.time,
                    it.title
                )
            }

        )
    }

}
