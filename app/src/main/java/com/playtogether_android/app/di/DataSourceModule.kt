package com.playtogether_android.app.di

import com.playtogether_android.data.datasource.onboarding.OnBoardingDataSource
import com.playtogether_android.data.datasource.onboarding.OnBoardingDataSourceImpl
import com.playtogether_android.data.datasource.sign.SignDataSource
import com.playtogether_android.data.datasource.sign.SignDataSourceImpl
import com.playtogether_android.data.datasource.thunder.ThunderDataSource
import com.playtogether_android.data.datasource.thunder.ThunderDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<SignDataSource> { SignDataSourceImpl(get()) }
    single<OnBoardingDataSource>{OnBoardingDataSourceImpl(get())}
    single<ThunderDataSource>{ThunderDataSourceImpl(get())}
}