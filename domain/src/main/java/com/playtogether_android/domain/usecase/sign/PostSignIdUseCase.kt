package com.playtogether_android.domain.usecase.sign

import com.playtogether_android.domain.model.sign.IdDuplicationCheckData
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem
import com.playtogether_android.domain.model.sign.SignUpData
import com.playtogether_android.domain.model.sign.SignUpItem
import com.playtogether_android.domain.repository.sign.SignRepository
import javax.inject.Inject

class PostSignIdUseCase @Inject constructor(private val repository: SignRepository) {
    suspend operator fun invoke(signIdDuplicationCheckItem: IdDuplicationCheckItem): IdDuplicationCheckData {
        return repository.postSignId(signIdDuplicationCheckItem)
    }
}