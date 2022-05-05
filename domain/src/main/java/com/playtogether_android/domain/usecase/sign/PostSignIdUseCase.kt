package com.playtogether_android.domain.usecase.sign

import com.playtogether_android.domain.model.sign.IdDuplicationCheckData
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem
import com.playtogether_android.domain.repository.sign.SignRepository

class PostSignIdUseCase(val repository: SignRepository) {

    suspend operator fun invoke(signIdDuplicationCheckItem: IdDuplicationCheckItem) : IdDuplicationCheckData{
        return repository.postSignId(signIdDuplicationCheckItem)
    }
}