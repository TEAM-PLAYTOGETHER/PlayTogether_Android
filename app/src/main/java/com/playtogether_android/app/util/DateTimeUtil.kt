package com.playtogether_android.app.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {
    private val birthFormat = SimpleDateFormat("yyyy", Locale.KOREA)
    private val remoteDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA)


    fun convertBirthFormat(str: String): String {
        return birthFormat.format(remoteDateFormat.parse(str)!!).toString()
    }

    fun convertTimeZone(str: String): String {
        //todo 수정이 필요할듯ㅋㅋㅋ
        remoteDateFormat.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val date = remoteDateFormat.parse(str)
        return outputFormat.format(date)
    }
}
