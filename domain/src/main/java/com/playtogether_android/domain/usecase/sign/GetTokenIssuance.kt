package com.playtogether_android.domain.usecase.sign

import com.playtogether_android.domain.model.sign.IssuanceItem
import com.playtogether_android.domain.repository.sign.SignRepository
import javax.inject.Inject

class GetTokenIssuance @Inject constructor(private val repository: SignRepository) {
    suspend operator fun invoke(refreshToken: String): IssuanceItem {
        return repository.getTokenIssuance(refreshToken)
    }
}
