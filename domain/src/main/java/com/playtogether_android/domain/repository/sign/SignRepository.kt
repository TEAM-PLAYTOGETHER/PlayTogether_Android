package com.playtogether_android.domain.repository.sign

import com.playtogether_android.domain.model.sign.IdDuplicationCheckData
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem

interface SignRepository {
    suspend fun postSignId(idDuplicationCheckItem: IdDuplicationCheckItem) : IdDuplicationCheckData
}