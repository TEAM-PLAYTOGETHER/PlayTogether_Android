package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.ThunderTabListData
import com.playtogether_android.domain.repository.thunder.ThunderRepository
import javax.inject.Inject

class GetApplyListUseCase @Inject constructor(private val repository: ThunderRepository) {

    suspend operator fun invoke(): ThunderTabListData {
        return repository.getApplyList()
    }

}