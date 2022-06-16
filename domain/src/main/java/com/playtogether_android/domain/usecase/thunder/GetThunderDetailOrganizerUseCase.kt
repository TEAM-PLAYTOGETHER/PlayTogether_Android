package com.playtogether_android.domain.usecase.thunder

import com.playtogether_android.domain.model.thunder.Organizer
import com.playtogether_android.domain.repository.thunder.ThunderRepository
import javax.inject.Inject

class GetThunderDetailOrganizerUseCase @Inject constructor(
    private val repo: ThunderRepository
) {
    suspend operator fun invoke(thunderId: Int): Organizer {
        lateinit var data: Organizer
        repo.getThunderDetailOrganizer(thunderId)
            .map { data = Organizer(it.name, it.organizerId, it.userLoginId) }
        return data
    }
}