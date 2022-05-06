package com.playtogether_android.data.repositoryimpl.sign

import com.playtogether_android.data.datasource.sign.SignDataSource
import com.playtogether_android.data.mapper.sign.SignMapper
import com.playtogether_android.domain.model.sign.IdDuplicationCheckData
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem
import com.playtogether_android.domain.model.sign.SignUpData
import com.playtogether_android.domain.model.sign.SignUpItem
import com.playtogether_android.domain.repository.sign.SignRepository

class SignRepositoryImpl(private val signDataSource: SignDataSource) : SignRepository {

    //아이디 중복확인
    override suspend fun postSignId(idDuplicationCheckItem: IdDuplicationCheckItem): IdDuplicationCheckData {
        return SignMapper.mapperToIdDuplicationData(signDataSource.postSignId(
            SignMapper.mapperToIdDuplicationItem(idDuplicationCheckItem)
        ))
    }

    override suspend fun postSignUp(signUpItem: SignUpItem): SignUpData {
        return SignMapper.mapperToSignupData(signDataSource.postSignUp(
            SignMapper.mapperToSignupItem(signUpItem)
        ))
    }
}