package com.playtogether_android.data.mapper.onboarding

import com.playtogether_android.data.model.request.onboarding.RequestCrew
import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.response.onboarding.ResponseCrew
import com.playtogether_android.data.model.response.onboarding.ResponseRegisterCrew
import com.playtogether_android.domain.model.onboarding.CrewData
import com.playtogether_android.domain.model.onboarding.CrewItem
import com.playtogether_android.domain.model.onboarding.RegisterCrewData
import com.playtogether_android.domain.model.onboarding.RegisterCrewItem

object OnBoardingMapper {
    //동아리 참여 : Response
    fun mapperToRegisterCrewData(responseRegisterCrew: ResponseRegisterCrew) : RegisterCrewData {
        return RegisterCrewData(
            success = responseRegisterCrew.success,
            crewName = responseRegisterCrew.data.crewName
        )
    }

    //동아리 참여 : Request
    fun mapperToRegisterCrewItem(registerCrewItem: RegisterCrewItem) : RequestRegisterCrew {
        return RequestRegisterCrew(
            crewCode = registerCrewItem.crewCode
        )
    }

    //동아리 생성 : Response
    fun mapperToCrewData(responseCrew: ResponseCrew) : CrewData {
        return CrewData(
            code = responseCrew.data.code,
            id = responseCrew.data.id,
            name = responseCrew.data.name
        )
    }

    fun mapperToCrewItem(crewItem: CrewItem) : RequestCrew {
        return RequestCrew(
            crewName = crewItem.crewName,
            description = crewItem.description
        )
    }
}