package com.playtogether_android.data.mapper.thunder

import androidx.lifecycle.Transformations.map
import com.playtogether_android.data.model.response.thunder.ResThunderTabListData
import com.playtogether_android.domain.model.thunder.ThunderTabListData

object ThunderMapper {

    //번개탭-신청한 번개 리스트
    fun mapperToThunderTabListData(resThunderTabListData: ResThunderTabListData) : ThunderTabListData {
        return ThunderTabListData(
            data = resThunderTabListData.data.map {
                ThunderTabListData.Data(it.lightId, it.title, it.date, it.time, it.peopleCnt, it.place, it.lightMemberCnt)
            }
        )
    }
}