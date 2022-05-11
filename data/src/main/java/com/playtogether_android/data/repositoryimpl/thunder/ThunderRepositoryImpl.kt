package com.playtogether_android.data.repositoryimpl.thunder

import com.playtogether_android.data.datasource.thunder.ThunderDataSource
import com.playtogether_android.data.mapper.thunder.ThunderMapper
import com.playtogether_android.domain.model.thunder.ThunderTabListData
import com.playtogether_android.domain.repository.thunder.ThunderRepository

class ThunderRepositoryImpl(private val thunderDataSource: ThunderDataSource) : ThunderRepository {

    override suspend fun getApplyList(): ThunderTabListData {
        return ThunderMapper.mapperToThunderTabListData(thunderDataSource.getApplyList())
    }
}