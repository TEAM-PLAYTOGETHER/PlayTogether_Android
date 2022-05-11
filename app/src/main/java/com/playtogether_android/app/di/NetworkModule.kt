package com.playtogether_android.app.di

import com.playtogether_android.data.api.message.MessageService
import com.playtogether_android.data.api.onboarding.OnboardingService
import com.playtogether_android.data.api.sign.SignService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module{
    single<SignService>{
        get<Retrofit>().create(SignService::class.java)
    }

    single<OnboardingService>{
        get<Retrofit>().create(OnboardingService::class.java)
    }

    single<MessageService>{
        get<Retrofit>().create(MessageService::class.java)
    }
}