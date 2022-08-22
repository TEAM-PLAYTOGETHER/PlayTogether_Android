package com.playtogether_android.data.mapper.thunder

import android.util.Log
import com.playtogether_android.data.model.request.thunder.RequestThunderCreate
import com.playtogether_android.data.model.response.thunder.ResponseThunderCreate
import com.playtogether_android.domain.model.thunder.GetThunderCreateData
import com.playtogether_android.domain.model.thunder.PostMultipartThunderCreateData
import com.playtogether_android.domain.model.thunder.PostThunderCreateData
import kotlin.properties.Delegates

object ThunderCreateMapper {
    fun mapperToGetCreateThunder(responseThunderCreate: ResponseThunderCreate): GetThunderCreateData {
        var _lightId = 0
        var _crewId = 0
        responseThunderCreate.data.map {
            _lightId = it.id
            _crewId = it.crewId
        }

        val g = GetThunderCreateData(
            message = responseThunderCreate.message,
            status = responseThunderCreate.status,
            success = responseThunderCreate.success,
            lightId = _lightId,
            crewId = _crewId
        )
        Log.d("createServerMapper", "${g.message}, ${g.status}, ${g.success}")
        return g
    }

    fun mapperToPostCreateThunder(postThunderCreateData: PostThunderCreateData): RequestThunderCreate {
        val r = RequestThunderCreate(
            title = postThunderCreateData.title,
            category = postThunderCreateData.category,
            date = postThunderCreateData.date,
            time = postThunderCreateData.time,
            place = postThunderCreateData.place,
            peopleCnt = postThunderCreateData.peopleCnt,
            description = postThunderCreateData.description,
        )
        Log.d(
            "createServerMapper",
            "${r.title} ${r.category} ${r.date} ${r.time} ${r.place} ${r.peopleCnt} ${r.description}"
        )
        return r
    }
}