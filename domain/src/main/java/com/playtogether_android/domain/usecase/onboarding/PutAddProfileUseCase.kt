package com.playtogether_android.domain.usecase.onboarding

import com.playtogether_android.domain.model.onboarding.*
import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository
import javax.inject.Inject

class PutAddProfileUseCase @Inject constructor(private val repository: OnBoardingRepository) {
    suspend operator fun invoke(addProfileItem: AddProfileItem, crewId : Int) : AddProfileData {
        return repository.putAddProfile(addProfileItem, crewId)
    }
}