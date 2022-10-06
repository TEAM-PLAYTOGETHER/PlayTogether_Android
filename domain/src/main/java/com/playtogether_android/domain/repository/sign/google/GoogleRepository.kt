package com.playtogether_android.domain.repository.sign.google

import com.playtogether_android.domain.model.sign.google.ReqGoogleAccess
import com.playtogether_android.domain.model.sign.google.ResGoogleAccess

interface GoogleRepository {
    suspend fun getAccessToken(request: ReqGoogleAccess): ResGoogleAccess
}