package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.Member
import com.playtogether_android.domain.repository.thunder.ThunderRepository

class GetThunderDetailMemberUseCase
    (private val repo: ThunderRepository) {
    suspend operator fun invoke(thunderId: Int): List<Member> {
        return repo.getThunderDetailMember(thunderId)
    }
}