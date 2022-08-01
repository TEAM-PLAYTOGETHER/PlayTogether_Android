package com.playtogether_android.domain.repository.onboarding


import com.playtogether_android.domain.model.onboarding.SubwayListData

interface SubwayRepository {

    suspend fun getSubwayList() : SubwayListData

}