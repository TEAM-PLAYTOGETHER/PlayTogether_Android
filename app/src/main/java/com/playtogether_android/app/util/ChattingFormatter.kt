package com.playtogether_android.app.util

import com.playtogether_android.domain.model.message.ChatData
import com.playtogether_android.domain.model.message.ChattingData
import com.playtogether_android.domain.model.message.DateData

class ChatDateStringReformat() {
    private val timeRange = IntRange(11, 15)
    private val dateRange = IntRange(0, 9)
    private val monthDayRange = IntRange(5, 9)
    private val dayRange = IntRange(8, 9)

    fun all(exFormatDate: String): String {
        val date = exFormatDate.slice(dateRange).replace("-", "/")
        val time = exFormatDate.slice(timeRange)
        return "$date  $time"
    }

    fun exceptYear(exFormatDate: String): String {
        val date = exFormatDate.slice(monthDayRange).replace("-", "/")
        val time = exFormatDate.slice(timeRange)
        return "$date  $time"
    }

    fun exceptTime(exFormatDate: String): String {
        val temp = exFormatDate.slice(dateRange)
        val year = temp.slice(IntRange(0, 3))
        val month = temp.slice(IntRange(5, 6))
        val day = temp.slice(IntRange(8, 9))
        return year + "년 " + month + "월 " + day + "일"
    }

    fun getDay(exFormatDate: String): String {
        return exFormatDate.slice(dayRange)
    }

    fun onlyTime(exFormatDate: String): String {
        return exFormatDate.slice(timeRange)
    }

    fun setChatListShownTime(list: List<ChatData>): List<ChatData> {
        if (list.isEmpty()) return list
        val tempList: List<ChatData> = list
        for (i in list.indices)
            tempList[i].shownTime = onlyTime(list[i].time)
        return tempList
    }

    fun processDate(
        list: List<ChatData>,
        prevChatData: ChatData?
    ): Pair<List<ChattingData>, Boolean> {
        if (list.isEmpty()) return Pair(list, true)
        val dateIndexList = mutableListOf<Int>()
        val dateContentList = mutableListOf<String>()
        val resultList = mutableListOf<ChattingData>()
        var prevProfileVisible = true

        if (isDayChanged(prevChatData, list[0])) {
            dateIndexList.add(1)
            dateContentList.add(prevChatData!!.time)
        }
        if (isTimeSame(prevChatData, list[0])) {
            list[0].timeVisible = false
            prevProfileVisible = false
        }

        for (i in 0 until list.size - 1) {
            val item1 = list[i]
            val item2 = list[i + 1]
            if (isDayChanged(item1, item2)) {
                dateIndexList.add(i + 1)
                dateContentList.add(item1.time)
            }
            if (isTimeSame(item1, item2)) {
                list[i + 1].timeVisible = false
                list[i].profileVisible = false
            }
            list[i].shownTime = onlyTime(list[i].time)
        }
        list[list.size - 1].shownTime = onlyTime(list[list.size - 1].time)

        resultList.addAll(list)
        for (i in dateIndexList.indices) {
            resultList.add(dateIndexList[i] + i, DateData(date = exceptTime(dateContentList[i])))
        }
        return Pair(resultList, prevProfileVisible)
    }

    fun isTimeSame(item1: ChatData?, item2: ChatData): Boolean {
        if (item1 == null) return false
        if (item1.messageType == item2.messageType) {
            if (all(item1.time) == all(item2.time))
                return true
        }
        return false
    }

    fun isDayChanged(item1: ChatData?, item2: ChatData): Boolean {
        if (item1 == null) return false
        return (getDay(item1.time) != getDay(item2.time))
    }

    fun reformatSocketChat(
        content: String,
        createdAt: String,
        amI: Boolean
    ): ChatData {
        return ChatData(
            messageId = null,
            content = content,
            time = createdAt,
            messageType = amI,
            shownTime = onlyTime(createdAt),
        )
    }
}