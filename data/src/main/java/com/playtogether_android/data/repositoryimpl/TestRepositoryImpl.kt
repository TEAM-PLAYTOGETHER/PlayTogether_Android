package com.playtogether_android.data.repositoryimpl

import com.playtogether_android.data.datasource.TestDataSource
import com.playtogether_android.data.mapper.TestMapper
import com.playtogether_android.domain.model.TestData
import com.playtogether_android.domain.repository.TestRepository

class TestRepositoryImpl (private val dataSource: TestDataSource) : TestRepository {
    override suspend fun getTest(): TestData {
        return TestMapper.mapperToTest(dataSource.getTestData())
    }
}