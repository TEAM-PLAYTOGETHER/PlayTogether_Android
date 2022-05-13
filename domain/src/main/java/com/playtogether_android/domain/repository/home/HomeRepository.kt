package com.playtogether_android.domain.repository.home

import com.playtogether_android.domain.model.home.JoinThunderData
import com.playtogether_android.domain.model.home.ThunderJoinEndData
import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.model.sign.*

interface HomeRepository {

    suspend fun postJoinThunder(lightId: Int) : JoinThunderData

    suspend fun getThunderJoinEnd(lightId: Int) : ThunderJoinEndData
}