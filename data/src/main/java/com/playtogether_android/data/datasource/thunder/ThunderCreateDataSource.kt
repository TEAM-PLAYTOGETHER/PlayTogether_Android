package com.playtogether_android.data.datasource.thunder

import com.playtogether_android.data.model.request.thunder.RequestThunderCreate
import com.playtogether_android.data.model.response.thunder.ResponseThunderCreate
import com.playtogether_android.domain.model.thunder.PostThunderCreateData

interface ThunderCreateDataSource {
    suspend fun postThunderCreate(crewId: Int, requestThunderCreate: RequestThunderCreate) : ResponseThunderCreate
}