package com.playtogether_android.data.datasource.light

import com.playtogether_android.data.api.light.LightService
import com.playtogether_android.data.model.response.light.ResponseLightJoinCancel
import com.playtogether_android.data.model.response.light.ResponseLightListCategory

class LightDataSourceImpl(private val lightService: LightService) : LightDataSource {

    override suspend fun getLightListCategory(
        category: String,
        sort: String
    ): ResponseLightListCategory {
        return lightService.getLightListCategory(category, sort)
    }
}