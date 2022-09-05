package com.playtogether_android.data.repositoryimpl.mypage

import com.playtogether_android.data.datasource.mypage.MyPageDataSource
import com.playtogether_android.data.datasource.sign.SignDataSource
import com.playtogether_android.data.mapper.mypage.MyPageMapper
import com.playtogether_android.data.mapper.sign.SignMapper
import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.model.mypage.UserDeleteData
import com.playtogether_android.domain.model.sign.*
import com.playtogether_android.domain.repository.mypage.MyPageRepository
import com.playtogether_android.domain.repository.sign.SignRepository

class MyPageRepositoryImpl(private val myPageDataSource: MyPageDataSource) : MyPageRepository {
    override suspend fun getUserCheck(userLoginId: String): UserCheckData {
        return MyPageMapper.mapperToUserCheck(myPageDataSource.getUserCheck(userLoginId))
    }

    override suspend fun deleteUser(): UserDeleteData {
        return MyPageMapper.mapperToUserDelete(myPageDataSource.deleteUser())
    }


}