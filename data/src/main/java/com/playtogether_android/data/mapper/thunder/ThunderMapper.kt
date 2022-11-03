package com.playtogether_android.data.mapper.thunder

import com.playtogether_android.data.model.ResGenericData
import com.playtogether_android.data.model.request.thunder.ReqReportData
import com.playtogether_android.data.model.response.thunder.*
import com.playtogether_android.domain.model.GenericData
import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.model.thunder.*

object ThunderMapper {

    //번개탭-신청한 번개 리스트
    fun mapperToThunderTabListData(resThunderTabListData: ResThunderTabListData): ThunderTabListData {
        return ThunderTabListData(
            data = resThunderTabListData.data.lightData.map {
                CategoryData(
                    it.category,
                    it.date,
                    it.lightId,
                    it.lightMemberCnt,
                    it.peopleCnt,
                    it.place,
                    it.time,
                    it.title,
                    it.scpCnt,
                    it.isOpened,
                )
            }
//            data = resThunderTabListData.data.lightData {
//                ThunderTabListData.Data(
//                    it.lightId,
//                    it.title,
//                    it.category,
//                    it.scpCnt,
//                    it.date,
//                    it.time,
//                    it.peopleCnt,
//                    it.place,
//                    it.lightMemberCnt,
//                    it.isOpened
//                )
//            }
        )
    }


    fun mapperToThunderJoinCancel(it: ResponseThunderJoinCancel): ThunderJoinCancelData {
        return ThunderJoinCancelData(
            status = it.status,
            success = it.success,
            message = it.message
        )
    }

    fun mapperToThunderDetailMember(it: List<ResThunderDetailData.Data>): List<Member> {
        val list = mutableListOf<Member>()
        it.map {
            list.addAll(it.members.map {
                Member(
                    it.age,
                    it.gender,
                    it.name,
                    it.userId,
                    it.image
                )
            })
        }
        return list
    }

    fun mapperToThunderDetailOrganizer(it: List<ResThunderDetailData.Data>): List<Organizer> {
        val list = mutableListOf<Organizer>()
        it.map {
            list.addAll(it.organizer.map {
                Organizer(
                    it.name,
                    it.organizerId,
                    it.image
                )
            })
        }
        return list
    }

    fun mapperToThunderDetail(it: ResThunderDetailData): List<ThunderDetailData> {
        return it.data.map {
            ThunderDetailData(
                it.category,
                it.date,
                it.description,
                it.image,
                it.isOpened,
                it.lightId,
                it.lightMemberCnt,
                it.peopleCnt,
                it.place,
                it.scpCnt,
                it.time,
                it.title
            )
        }
    }

    //번개 삭제
    fun mapperToThunderDelete(it: ResThunderDeleteData): ThunderDeleteData {
        return ThunderDeleteData(
            status = it.status,
            success = it.success,
            message = it.message
        )
    }

    //번개 참여,오픈 여부
    fun mapperToThunderExistCheck(data: ResThunderExistCheckData.Data): GetThunderExistCheck {
        return GetThunderExistCheck(
            data.isEntered,
            data.isOrganizer
        )
    }

    // 번개 게시글 신고 : Request
    fun mapperToReportItem(reportData: ReportData): ReqReportData {
        return ReqReportData(
            report = reportData.report
        )
    }

    // 번개 게시글 신고 : Response
    fun mapperToReportData(resGenericData: ResGenericData): GenericData {
        return GenericData(
            success = resGenericData.success,
            status = resGenericData.status,
            message = resGenericData.message

        )
    }

}