package com.playtogether_android.data.repositoryimpl.thunder

import com.playtogether_android.data.datasource.thunder.ThunderCreateDataSource
import com.playtogether_android.data.mapper.thunder.ThunderCreateMapper
import com.playtogether_android.data.model.response.thunder.ResponseThunderCreate
import com.playtogether_android.domain.model.thunder.GetThunderCreateData
import com.playtogether_android.domain.model.thunder.PostMultipartThunderCreateData
import com.playtogether_android.domain.model.thunder.PostThunderCreateData
import com.playtogether_android.domain.repository.thunder.ThunderCreateRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ThunderCreateRepositoryImpl(private val thunderCreateDataSource: ThunderCreateDataSource) :
    ThunderCreateRepository {
    override suspend fun postThunderCreate(
        crewId: Int,
        postThunderCreateData: PostThunderCreateData
    ): GetThunderCreateData {
        return ThunderCreateMapper.mapperToGetCreateThunder(
            thunderCreateDataSource.postThunderCreate(
                crewId, ThunderCreateMapper.mapperToPostCreateThunder(postThunderCreateData)
            )
        )
    }

    override suspend fun postMultipartThunderCreate(
        crewId: Int,
        body: HashMap<String, RequestBody>,
        image: MultipartBody.Part?
    ) {

    }
}