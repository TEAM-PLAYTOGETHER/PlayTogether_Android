package com.playtogether_android.domain.model

data class TestData(
    val data: List<Data>
) {
    data class Data(
        val company_id: String,
        val company_name: String,
        val coordx: String,
        val coordy: String,
        val dev_id: String,
        val ison: Boolean,
        val loc: String,
        val name: String,
        val pm10_after: Int,
        val pm25_after: Int,
        val state: Int,
        val timestamp: String
    )
}