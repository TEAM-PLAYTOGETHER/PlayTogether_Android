package com.playtogether_android.data.repositoryimpl.sign.google

import com.playtogether_android.data.datasource.sign.google.GoogleDataSource
import com.playtogether_android.data.mapper.sign.google.GoogleMapper
import com.playtogether_android.domain.model.sign.google.ReqGoogleAccess
import com.playtogether_android.domain.model.sign.google.ResGoogleAccess
import com.playtogether_android.domain.repository.sign.google.GoogleRepository

class GoogleRepositoryImpl(private val dataSource: GoogleDataSource) : GoogleRepository {
    override suspend fun getAccessToken(request: ReqGoogleAccess): ResGoogleAccess {
        return GoogleMapper.mapperToResAccessToken(
            dataSource.googleGetToken(
                GoogleMapper.mapperToReqAccessToken(
                    request
                )
            )
        )
    }
}