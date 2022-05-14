package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.ThunderTempDetailData
import com.playtogether_android.domain.repository.thunder.ThunderRepository

class GetThunderTempDetailUseCase(private val repo: ThunderRepository) {
    suspend operator fun invoke(thunderId: Int): ThunderTempDetailData {
        lateinit var data: ThunderTempDetailData
        repo.getThunderDetail(thunderId).map {
            data = ThunderTempDetailData(
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