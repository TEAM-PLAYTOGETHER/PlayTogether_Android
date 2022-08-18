package com.playtogether_android.data.mapper.onboarding


import com.playtogether_android.data.model.request.onboarding.RequestAddProfile
import com.playtogether_android.data.model.request.onboarding.RequestMakeCrew
import com.playtogether_android.data.model.request.onboarding.RequestRegisterCrew
import com.playtogether_android.data.model.response.onboarding.*
import com.playtogether_android.domain.model.onboarding.*

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


    //동아리 개설 response
    fun mapperToMakeCrewData(responseMakeCrew: ResponseMakeCrew) : MakeCrewData {
        return MakeCrewData(
            success = responseMakeCrew.success,
            code = responseMakeCrew.data.code,
            id = responseMakeCrew.data.id,
            name = responseMakeCrew.data.name
        )
    }

    //동아리 개설 request
    fun mapperToMakeCrewItem(makeCrewItem: MakeCrewItem) : RequestMakeCrew{
        return RequestMakeCrew(
            crewName = makeCrewItem.crewName,
            description = makeCrewItem.description
        )
    }

    //동아리 리스트 조회 response
    fun mapperToGetList(responseGetList: ResponseGetList) :CrewListData{
        return CrewListData(
            data = CrewListData.Data(
                crewList = responseGetList.data.list.map {
                    CrewListData.Data.CrewList(
                        id = it.id,
                        name = it.name,
                        description = it.description ?: ""
                    )
                }
            ),
            success = responseGetList.success
        )
    }


    //지하철 조회 response
    fun mapperToSubwayList(responseSubwayList: ResponseSubwayList) : List<SubwayListData> {
        return responseSubwayList.SearchSTNBySubwayLineInfo.row.map {
            SubwayListData(
                FR_CODE = it.FR_CODE,
                LINE_NUM = it.LINE_NUM,
                STATION_CD = it.STATION_CD,
                STATION_NM = it.STATION_NM,
                STATION_NM_ENG = it.STATION_NM_ENG
            )
        }
    }

    fun mapperToNicknameDuplication(responseGetNickNameDuplication: ResponseGetNickNameDuplication) : NickNameDuplicationData {
        return NickNameDuplicationData(
            status = responseGetNickNameDuplication.status,
            success = responseGetNickNameDuplication.success
        )
    }

    //멀티 프로필 등록 response
    fun mapperToAddProfileData(responseAddProfile: ResponseAddProfile) : AddProfileData {
        return AddProfileData(
            status = responseAddProfile.status,
            success = responseAddProfile.success
        )
    }


    //멀티 프로필 등록 request
    fun mapperToAddProfileItem(addProfileItem: AddProfileItem) : RequestAddProfile {
        return RequestAddProfile(
            description = addProfileItem.description,
            firstStation = addProfileItem.firstStation,
            nickname = addProfileItem.nickname,
            secondStation = addProfileItem.secondStation
        )
    }
}