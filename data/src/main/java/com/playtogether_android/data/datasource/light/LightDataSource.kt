package com.playtogether_android.data.datasource.light

import com.playtogether_android.data.model.response.light.ResponseLightJoinCancel
import com.playtogether_android.data.model.response.light.ResponseLightListCategory

interface LightDataSource {
    suspend fun getLightListCategory(
        category: String,
        sort: String,
    ): ResponseLightListCategory
}