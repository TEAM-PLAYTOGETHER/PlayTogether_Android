package com.playtogether_android.data.datasource

import com.playtogether_android.data.api.TestService
import com.playtogether_android.data.model.ResponseTestData

class TestDataSourceImpl(private val service : TestService) : TestDataSource  {
    override suspend fun getTestData(): ResponseTestData {
        return service.getTestData()
    }
}