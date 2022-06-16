package com.playtogether_android.domain.usecase.home

import com.playtogether_android.domain.model.home.JoinThunderData
import com.playtogether_android.domain.model.mypage.UserCheckData
import com.playtogether_android.domain.repository.home.HomeRepository
import com.playtogether_android.domain.repository.mypage.MyPageRepository
import javax.inject.Inject

class PostJoinThunderUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend operator fun invoke(lightId : Int) : JoinThunderData{
        return repository.postJoinThunder(lightId)
    }
}