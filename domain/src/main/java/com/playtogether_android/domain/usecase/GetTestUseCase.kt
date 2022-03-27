package com.playtogether_android.domain.usecase

import com.playtogether_android.domain.model.TestData
import com.playtogether_android.domain.repository.TestRepository

class GetTestUseCase (private val repository: TestRepository){
    suspend operator fun invoke() : TestData {
        return repository.getTest()
    }
}