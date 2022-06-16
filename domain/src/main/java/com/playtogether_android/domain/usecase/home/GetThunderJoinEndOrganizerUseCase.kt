package com.playtogether_android.domain.usecase.home

import com.playtogether_android.domain.model.home.ThunderJoinEndOrganizer
import com.playtogether_android.domain.model.thunder.Organizer
import com.playtogether_android.domain.repository.home.HomeRepository
import com.playtogether_android.domain.repository.thunder.ThunderRepository
import javax.inject.Inject

class GetThunderJoinEndOrganizerUseCase @Inject constructor(private val repo: HomeRepository) {
    suspend operator fun invoke(lightId: Int): ThunderJoinEndOrganizer {
        lateinit var data: ThunderJoinEndOrganizer
        repo.getThunderJoinEndOrganizer(lightId)
            .map { data = ThunderJoinEndOrganizer(it.name, it.organizer_id) }
        return data
    }
}