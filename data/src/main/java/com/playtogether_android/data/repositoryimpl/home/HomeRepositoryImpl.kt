package com.playtogether_android.data.repositoryimpl.home

import com.playtogether_android.data.datasource.home.HomeDataSource
import com.playtogether_android.data.datasource.mypage.MyPageDataSource
import com.playtogether_android.data.datasource.sign.SignDataSource
import com.playtogether_android.data.mapper.home.HomeMapper
import com.playtogether_android.data.mapper.mypage.MyPageMapper
import com.playtogether_android.data.mapper.sign.SignMapper
import com.playtogether_android.domain.model.home.JoinThunderData
import com.playtogether_android.domain.model.home.ThunderJoinEndData
import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.model.sign.*
import com.playtogether_android.domain.repository.home.HomeRepository
import com.playtogether_android.domain.repository.mypage.MyPageRepository
import com.playtogether_android.domain.repository.sign.SignRepository

class HomeRepositoryImpl(private val homeDataSource: HomeDataSource) : HomeRepository {
    override suspend fun postJoinThunder(lightId: Int): JoinThunderData {
        return HomeMapper.mapperToJoinThunder(homeDataSource.postJoinThunder(lightId))
    }

    override suspend fun getThunderJoinEnd(lightId: Int): ThunderJoinEndData {
        return HomeMapper.mapperToThunderJoinEnd(homeDataSource.getThunderJoinEnd(lightId))
    }
}