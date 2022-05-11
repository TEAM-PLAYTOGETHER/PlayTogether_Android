package com.playtogether_android.app.di

import com.playtogether_android.data.repositoryimpl.light.LightRepositoryImpl
import com.playtogether_android.data.repositoryimpl.onboarding.OnBoardingRepositoryImpl
import com.playtogether_android.data.repositoryimpl.sign.SignRepositoryImpl
import com.playtogether_android.domain.repository.light.LightRepository
import com.playtogether_android.data.repositoryimpl.thunder.ThunderRepositoryImpl
import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository
import com.playtogether_android.domain.repository.sign.SignRepository
import com.playtogether_android.domain.repository.thunder.ThunderRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SignRepository> { SignRepositoryImpl(get()) }
    single<LightRepository> { LightRepositoryImpl(get()) }
//    single<SignRepository>{ SignRepositoryImpl(get()) }

    single<OnBoardingRepository>{OnBoardingRepositoryImpl(get())}
    single<ThunderRepository>{ThunderRepositoryImpl(get())}
}
