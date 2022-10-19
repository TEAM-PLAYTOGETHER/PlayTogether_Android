package com.playtogether_android.app.util

import com.playtogether_android.app.presentation.ui.message.adapter.ChatData2
import com.playtogether_android.app.presentation.ui.message.adapter.ChattingData
import com.playtogether_android.app.presentation.ui.message.adapter.DateData

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
        return exFormatDate.slice(dateRange).replace("-", "/")
    }

    fun getDay(exFormatDate: String): String {
        return exFormatDate.slice(dayRange)
    }

    fun onlyTime(exFormatDate: String): String {
        return exFormatDate.slice(timeRange)
    }

    fun setChatListShownTime(list: List<ChatData2>): List<ChatData2> {
        if (list.isEmpty()) return list
        val tempList: List<ChatData2> = list
        for (i in list.indices)
            tempList[i].shownTime = onlyTime(list[i].time)
        return tempList
    }

    fun insertDay(list: List<ChatData2>, prevChatData: ChatData2) {
        if (list.isEmpty()) return
        val tempList: MutableList<ChattingData> = list.toMutableList()
        var index = 0
        while (true) {
            if (index >= tempList.size - 1) break
            val item1 = tempList[index]
            val item2 = tempList[index + 1]
            if ((item1 !is ChatData2) or (item2 !is ChatData2)) {
                index++
                continue
            }
            if (isDayChanged((item1 as ChatData2).time, (item2 as ChatData2).time)) {
                tempList.add(index + 1, DateData(date = exceptTime(item2.time)))
                index += 2
            } else index++
        }

        //prevChatDate와 만나는 부분 구현 필요
    }

    fun isDayChanged(date1: String, date2: String): Boolean {
        return (getDay(date1) != getDay(date2))
    }

    fun reformatSocketChat(
        lastId: Int,
        content: String,
        createdAt: String,
        amI: Boolean
    ): ChatData2 {
        return ChatData2(
            messageId = lastId + 1,
            content = content,
            time = createdAt,
            messageType = amI,
            shownTime = onlyTime(createdAt),
        )
    }
}