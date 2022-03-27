package com.playtogether_android.data.datasource

import com.playtogether_android.data.model.ResponseTestData

interface TestDataSource {
    suspend fun getTestData(): ResponseTestData
}