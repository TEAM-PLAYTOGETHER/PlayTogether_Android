package com.playtogether_android.domain.repository.onboarding

import com.playtogether_android.domain.model.onboarding.CrewData
import com.playtogether_android.domain.model.onboarding.CrewItem
import com.playtogether_android.domain.model.onboarding.RegisterCrewData
import com.playtogether_android.domain.model.onboarding.RegisterCrewItem

interface OnBoardingRepository {
    suspend fun postRegisterCrew(registerCrewItem: RegisterCrewItem) : RegisterCrewData

    suspend fun postCrew(crewItem : CrewItem) : CrewData
}