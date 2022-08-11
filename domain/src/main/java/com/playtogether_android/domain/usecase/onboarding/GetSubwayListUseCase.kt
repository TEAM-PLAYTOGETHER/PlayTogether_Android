package com.playtogether_android.domain.usecase.onboarding

import com.playtogether_android.domain.model.onboarding.*
import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository
import com.playtogether_android.domain.repository.onboarding.SubwayRepository
import javax.inject.Inject

class GetSubwayListUseCase @Inject constructor(private val repository: SubwayRepository) {
    suspend operator fun invoke() : List<SubwayListData> {
        return repository.getSubwayList()
    }
}