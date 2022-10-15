package com.playtogether_android.app.util

class DateStringReformat() {
    fun all(exFormatDate: String): String {
        val date = exFormatDate.slice(IntRange(0, 9)).replace("-", "/")
        val time = exFormatDate.slice(IntRange(11, 15))
        return "$date  $time"
    }

    fun exceptYear(exFormatDate: String): String {
        val date = exFormatDate.slice(IntRange(5, 9)).replace("-", "/")
        val time = exFormatDate.slice(IntRange(11, 15))
        return "$date  $time"
    }

    fun exceptTime(exFormatDate: String) : String{
        return exFormatDate.slice(IntRange(0, 9)).replace("-", "/")
    }

    fun getDay(exFormatDate: String) : String{
        return exFormatDate.slice(IntRange(8,9))
    }
}