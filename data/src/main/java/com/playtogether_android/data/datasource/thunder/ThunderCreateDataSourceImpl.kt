package com.playtogether_android.data.datasource.thunder

import android.util.Log
import com.playtogether_android.data.api.thunder.ThunderCreateService
import com.playtogether_android.data.model.request.thunder.RequestThunderCreate
import com.playtogether_android.data.model.response.thunder.ResponseThunderCreate
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ThunderCreateDataSourceImpl(private val service: ThunderCreateService) :
    ThunderCreateDataSource {
    override suspend fun postThunderCreate(
        crewId: Int,
        requestThunderCreate: RequestThunderCreate
    ): ResponseThunderCreate {
        val a = service.postThunderCreate(crewId, requestThunderCreate)
        Log.d("createThunder", "datasourceImpl")
        Log.d("createThunder", "${a.message}")
        Log.d("createThunder", "crewId${crewId}")
        return a
    }

    override suspend fun postMultipartThunderCreate(
        crewId: Int,
        body: HashMap<String, RequestBody>,
        image: MultipartBody.Part?
    ): ResponseThunderCreate {
        return service.postMultipartThunderCreate(crewId, body, image)
    }
}