package com.playtogether_android.domain.usecase.home

import com.playtogether_android.domain.model.home.ThunderJoinEndMember
import com.playtogether_android.domain.model.thunder.Member
import com.playtogether_android.domain.repository.home.HomeRepository
import com.playtogether_android.domain.repository.thunder.ThunderRepository
import javax.inject.Inject

class GetThunderJoinEndMemberUseCase @Inject constructor(private val repo: HomeRepository) {
    suspend operator fun invoke(light: Int): List<ThunderJoinEndMember> {
        return repo.getThunderJoinEndMember(light)
    }
}