package com.playtogether_android.data.datasource.thunder

import android.util.Log
import com.playtogether_android.data.api.thunder.ThunderCreateService
import com.playtogether_android.data.model.request.thunder.RequestThunderCreate
import com.playtogether_android.data.model.response.thunder.ResponseThunderCreate

class ThunderCreateDataSourceImpl(private val service : ThunderCreateService) : ThunderCreateDataSource {
    override suspend fun postThunderCreate(
        crewId: Int,
        requestThunderCreate: RequestThunderCreate
    ): ResponseThunderCreate {
        val a = service.postThunderCreate(crewId, requestThunderCreate)
        Log.d("createThunder", "datasourceImpl")
        Log.d("createThunder", "${a.message}")
        return a
    }
}