package com.playtogether_android.app.di

import com.playtogether_android.data.repositoryimpl.message.MessageRepositoryImpl
import com.playtogether_android.data.repositoryimpl.onboarding.OnBoardingRepositoryImpl
import com.playtogether_android.data.repositoryimpl.sign.SignRepositoryImpl
import com.playtogether_android.domain.repository.message.MessageRepository
import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository
import com.playtogether_android.domain.repository.sign.SignRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SignRepository>{ SignRepositoryImpl(get()) }

    single<OnBoardingRepository>{OnBoardingRepositoryImpl(get())}

    single<MessageRepository>{MessageRepositoryImpl(get())}
}
