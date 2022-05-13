package com.playtogether_android.domain.usecase.home

import com.playtogether_android.domain.model.home.ThunderJoinEndData
import com.playtogether_android.domain.repository.home.HomeRepository

class GetThunderJoinEndUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(lightId : Int) : ThunderJoinEndData.Data{
        return repository.getThunderJoinEnd(lightId)
    }
}