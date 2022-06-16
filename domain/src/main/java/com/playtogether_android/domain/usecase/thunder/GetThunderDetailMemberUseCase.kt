package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.Member
import com.playtogether_android.domain.repository.thunder.ThunderRepository
import javax.inject.Inject

class GetThunderDetailMemberUseCase
@Inject constructor(private val repo: ThunderRepository) {
    suspend operator fun invoke(thunderId: Int): List<Member> {
        return repo.getThunderDetailMember(thunderId)
    }
}