package com.playtogether_android.domain.usecase.onboarding

import com.playtogether_android.domain.model.onboarding.CrewData
import com.playtogether_android.domain.model.onboarding.CrewItem
import com.playtogether_android.domain.model.onboarding.RegisterCrewData
import com.playtogether_android.domain.model.onboarding.RegisterCrewItem
import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository
import javax.inject.Inject

class PostCrewUseCase @Inject constructor(private val repository: OnBoardingRepository) {
    suspend operator fun invoke(crewItem: CrewItem) : CrewData {
        return repository.postCrew(crewItem)
    }
}