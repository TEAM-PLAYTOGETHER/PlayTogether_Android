package com.playtogether_android.domain.usecase.onboarding

import com.playtogether_android.domain.model.onboarding.*
import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository
import javax.inject.Inject

class GetNicknameDuplicationUseCase @Inject constructor(private val repository: OnBoardingRepository) {
    suspend operator fun invoke(crewId : Int, nickname : String) : NickNameDuplicationData {
        return repository.getNickNameDuplication(crewId, nickname)
    }
}