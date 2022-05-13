package com.playtogether_android.data.mapper.thunder

import android.util.Log
import com.playtogether_android.data.model.request.thunder.RequestThunderCreate
import com.playtogether_android.data.model.response.thunder.ResponseThunderCreate
import com.playtogether_android.domain.model.thunder.GetThunderCreateData
import com.playtogether_android.domain.model.thunder.PostThunderCreateData

object ThunderCreateMapper {
    fun mapperToGetCreateThunder(responseThunderCreate: ResponseThunderCreate): GetThunderCreateData{
        val g = GetThunderCreateData(
            message = responseThunderCreate.message,
            status = responseThunderCreate.status,
            success = responseThunderCreate.success
        )
        Log.d("createServerMapper", "${g.message}, ${g.status}, ${g.success}")
        return g
    }

    fun mapperToPostCreateThunder(postThunderCreateData: PostThunderCreateData) : RequestThunderCreate{
        val r = RequestThunderCreate(
            title = postThunderCreateData.title,
            category = postThunderCreateData.category,
            date = postThunderCreateData.date,
            time = postThunderCreateData.time,
            place = postThunderCreateData.place,
            peoplCnt = postThunderCreateData.peoplCnt,
            description = postThunderCreateData.description
        )
        Log.d("createServerMapper", "${r.title} ${r.category} ${r.date} ${r.time} ${r.place} ${r.peoplCnt} ${r.description}")
        return r
    }
}