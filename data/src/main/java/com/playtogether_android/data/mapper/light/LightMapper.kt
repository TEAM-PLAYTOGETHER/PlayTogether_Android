package com.playtogether_android.data.mapper.light

import com.playtogether_android.data.model.response.light.ResponseHomeLightning
import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.model.light.HomeLightningData

object LightMapper {
    fun mapperToCategoryData(data: ResponseLightListCategory): List<CategoryData> {
        return data.data.lightData.map {
            CategoryData(
                category = it.category,
                date = it.date,
                lightId = it.lightId,
                lightMemberCnt = it.lightMemberCnt,
                peopleCnt = it.peopleCnt,
                place = it.place,
                time = it.time,
                title = it.title,
                likeCount = it.scpCnt,
                isOpen = it.isOpened
            )
        }
//        return data.data.map {
//            CategoryData(
//                category = it.category,
//                date = it.date,
//                lightId = it.lightId,
//                lightMemberCnt = it.lightMemberCnt,
//                peopleCnt = it.peopleCnt,
//                place = it.place,
//                time = it.time,
//                title = it.title,
//            )
//        }
    }

    fun mapperToHomeLightningData(data: ResponseHomeLightning): List<HomeLightningData> {
        return data.data.map {
            HomeLightningData(
                title = it.title,
                category = it.category,
                peopleCnt = it.peopleCnt,
                lightMemberCnt = it.lightMemberCnt,
                place = it.place,
                time = it.time,
                date = it.date,
                id = it.lightId
            )
        }
    }

}