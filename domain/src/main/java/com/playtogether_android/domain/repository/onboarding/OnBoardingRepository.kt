package com.playtogether_android.domain.repository.onboarding


import com.playtogether_android.domain.model.onboarding.*

interface OnBoardingRepository {
    suspend fun postRegisterCrew(registerCrewItem: RegisterCrewItem) : RegisterCrewData

    suspend fun postMakeCrew(makeCrewItem: MakeCrewItem) : MakeCrewData

    suspend fun getCrewList() : CrewListData

    suspend fun getNickNameDuplication(crewId : Int, nickname : String) : NickNameDuplicationData

}