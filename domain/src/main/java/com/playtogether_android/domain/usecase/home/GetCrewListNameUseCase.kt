package com.playtogether_android.domain.usecase.home

import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository
import javax.inject.Inject

class GetCrewListNameUseCase @Inject constructor(private val repository: OnBoardingRepository) {
    suspend operator fun invoke(): List<String> {
        return repository.getCrewList().data.crewList.map { it.name }
    }
}