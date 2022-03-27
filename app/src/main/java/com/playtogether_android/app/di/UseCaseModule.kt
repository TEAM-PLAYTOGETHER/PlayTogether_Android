package com.playtogether_android.app.di

import com.playtogether_android.domain.usecase.GetTestUseCase
import org.koin.dsl.module

val useCaseModule = module{
    single { GetTestUseCase(get()) }
}


