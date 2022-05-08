package com.playtogether_android.app.di

import com.playtogether_android.data.api.sign.SignService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module{
    single<SignService>{
        get<Retrofit>().create(SignService::class.java)
    }
}