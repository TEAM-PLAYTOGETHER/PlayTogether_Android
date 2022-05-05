package com.playtogether_android.app.di

import com.playtogether_android.domain.usecase.sign.PostSignIdUseCase
import org.koin.dsl.module

val useCaseModule = module{

    //sign
    single {PostSignIdUseCase(get())}
}


