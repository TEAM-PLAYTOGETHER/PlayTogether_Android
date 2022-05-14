package com.playtogether_android.domain.repository.home

import com.playtogether_android.domain.model.home.JoinThunderData
import com.playtogether_android.domain.model.home.ThunderJoinEndData
import com.playtogether_android.domain.model.home.ThunderJoinEndMember
import com.playtogether_android.domain.model.home.ThunderJoinEndOrganizer
import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.model.sign.*
import java.lang.reflect.Member

interface HomeRepository {

    suspend fun postJoinThunder(lightId: Int) : JoinThunderData

    suspend fun getThunderJoinEnd(lightId: Int) : List<ThunderJoinEndData>

    suspend fun getThunderJoinEndMember(lightId: Int) : List<ThunderJoinEndMember>

    suspend fun getThunderJoinEndOrganizer(lightId: Int) : List<ThunderJoinEndOrganizer>
}