package com.playtogether_android.domain.model.onboarding

data class SubwayListData(
    val SearchSTNBySubwayLineInfo: searchSTNBySubwayLineInfo
) {
    data class searchSTNBySubwayLineInfo(
        val RESULT: result,
        val list_total_count: Int,
        val row: List<Row>
    ) {
        data class result(
            val CODE: String,
            val MESSAGE: String
        )

        data class Row(
            val FR_CODE: String,
            val LINE_NUM: String,
            val STATION_CD: String,
            val STATION_NM: String,
            val STATION_NM_ENG: String
        )
    }
}