package com.playtogether_android.data.datasource.light

import com.playtogether_android.data.api.light.LightService
import com.playtogether_android.data.model.response.light.ResponseLightListCategory
import com.playtogether_android.data.model.response.light.ResponseHomeLightning

class LightDataSourceImpl(private val lightService: LightService) : LightDataSource {

    override suspend fun getLightListCategory(
        category: String,
        sort: String
    ): ResponseLightListCategory {
        return lightService.getLightListCategory(category, sort)
    }

    override suspend fun getNewLightning(): ResponseHomeLightning {
        return lightService.getNewLightning()
    }

    override suspend fun getHotLightning(): ResponseHomeLightning {
        return lightService.getHotLightning()
    }
}