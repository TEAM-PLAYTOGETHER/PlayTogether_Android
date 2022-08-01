package com.playtogether_android.domain.usecase.light

import com.playtogether_android.domain.model.light.HomeLightningData
import com.playtogether_android.domain.repository.light.LightRepository
import javax.inject.Inject

class GetHotListUseCase @Inject constructor(private val repository: LightRepository){
    suspend operator fun invoke() : List<HomeLightningData>{
        return repository.getHotLightning()
    }
}