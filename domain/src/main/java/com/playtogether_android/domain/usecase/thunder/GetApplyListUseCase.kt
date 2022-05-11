package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.ThunderTabListData
import com.playtogether_android.domain.repository.thunder.ThunderRepository

class GetApplyListUseCase(private val repository: ThunderRepository) {

    //번개탭-신청한 번개 리스트
    suspend operator fun invoke() : ThunderTabListData {
        return repository.getApplyList()
    }
}