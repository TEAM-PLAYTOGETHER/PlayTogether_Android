package com.playtogether_android.domain.usecase.onboarding

import com.playtogether_android.domain.model.onboarding.MakeCrewData
import com.playtogether_android.domain.model.onboarding.MakeCrewItem
import com.playtogether_android.domain.model.onboarding.RegisterCrewData
import com.playtogether_android.domain.model.onboarding.RegisterCrewItem
import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository
import javax.inject.Inject

class PostMakeCrewUseCase @Inject constructor(private val repository: OnBoardingRepository) {
    suspend operator fun invoke(makeCrewItem: MakeCrewItem) : MakeCrewData {
        return repository.postMakeCrew(makeCrewItem)
    }
}