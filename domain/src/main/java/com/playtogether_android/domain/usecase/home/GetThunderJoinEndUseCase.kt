package com.playtogether_android.domain.usecase.home

import com.playtogether_android.domain.model.home.ThunderJoinEndData
import com.playtogether_android.domain.model.thunder.ThunderDetailData
import com.playtogether_android.domain.repository.home.HomeRepository

class GetThunderJoinEndUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(lightId : Int) : ThunderJoinEndData {
        lateinit var data: ThunderJoinEndData
        repository.getThunderJoinEnd(lightId).map {
            data = ThunderJoinEndData(
                LightMemberCnt = it.LightMemberCnt,
                category = it.category,
                date = it.date,
                description = it.description,
                light_id = it.light_id,
                people_cnt = it.people_cnt,
                place = it.place,
                time = it.time,
                title = it.title
            )
        }
        return data
    }

}