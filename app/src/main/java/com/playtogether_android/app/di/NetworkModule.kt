package com.playtogether_android.app.di

import com.playtogether_android.data.api.TestService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module{
    single<TestService>{
        get<Retrofit>().create(TestService::class.java)
    }
}