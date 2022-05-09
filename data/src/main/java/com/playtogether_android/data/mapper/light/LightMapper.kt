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

//    fun mapperToData(data: ResponseLightListCategory.Category): List<CategoryData> {
//        return listOf(
//            CategoryData(
//                category = data.category,
//                date = data.date,
//                lightMemberCnt = data.lightMemberCnt,
//                lightId = data.lightId,
//                peopleCnt = data.peopleCnt,
//                place = data.place,
//                time = data.time,
//                title = data.title
//            )
//        )
//    }
}