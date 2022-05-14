package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.ThunderDetailData
import com.playtogether_android.domain.repository.thunder.ThunderRepository

class GetThunderDetailUseCase(private val repo: ThunderRepository) {
    suspend operator fun invoke(thunderId: Int): ThunderDetailData {
        lateinit var data: ThunderDetailData
        repo.getThunderDetail(thunderId).map {
            data = ThunderDetailData(
                it.category,
                it.date,
                it.description,
                it.lightId,
                it.lightMemberCnt,
                it.peopleCnt,
                it.place,
                it.time,
                it.title,
            )
        }
        return data
    }
}