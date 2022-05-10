package com.playtogether_android.data.mapper.light

import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import com.playtogether_android.domain.model.light.CategoryData

object LightMapper {
    fun mapperToCategoryData(data: ResponseLightListCategory): List<CategoryData> {
        return data.data.map {
            CategoryData(
                category = it.category,
                date = it.date,
                lightId = it.lightId,
                lightMemberCnt = it.lightMemberCnt,
                peopleCnt = it.peopleCnt,
                place = it.place,
                time = it.time,
                title = it.title,
            )
        }
    }
}