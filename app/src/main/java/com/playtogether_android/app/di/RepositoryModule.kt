package com.playtogether_android.app.di

import com.playtogether_android.data.repositoryimpl.light.LightRepositoryImpl
import com.playtogether_android.data.repositoryimpl.sign.SignRepositoryImpl
import com.playtogether_android.domain.repository.light.LightRepository
import com.playtogether_android.domain.repository.sign.SignRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SignRepository> { SignRepositoryImpl(get()) }
    single<LightRepository> { LightRepositoryImpl(get()) }
}
