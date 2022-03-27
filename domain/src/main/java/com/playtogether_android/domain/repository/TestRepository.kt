package com.playtogether_android.domain.repository

import com.playtogether_android.domain.model.TestData

interface TestRepository {
    suspend fun getTest() : TestData
}