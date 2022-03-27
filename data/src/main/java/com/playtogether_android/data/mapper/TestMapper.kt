package com.playtogether_android.data.mapper

import com.playtogether_android.data.model.ResponseTestData
import com.playtogether_android.domain.model.TestData

object TestMapper {
    fun mapperToTest(responseTestData: ResponseTestData) : TestData {
        return TestData(
            data = responseTestData.data.map {
                TestData.Data(
                    company_id = it.company_id,
                    company_name = it.company_name,
                    coordx = it.coordx,
                    coordy = it.coordy,
                    dev_id = it.dev_id,
                    ison = it.ison,
                    loc = it.loc,
                    name = it.name,
                    pm10_after = it.pm10_after,
                    pm25_after = it.pm25_after,
                    state = it.state,
                    timestamp = it.timestamp
                )
            }
        )
    }
}