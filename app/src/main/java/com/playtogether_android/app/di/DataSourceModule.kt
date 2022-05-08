package com.playtogether_android.app.di

import com.playtogether_android.data.datasource.light.LightDataSource
import com.playtogether_android.data.datasource.light.LightDataSourceImpl
import com.playtogether_android.data.datasource.sign.SignDataSource
import com.playtogether_android.data.datasource.sign.SignDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<SignDataSource> { SignDataSourceImpl(get()) }
    single<LightDataSource> { LightDataSourceImpl(get()) }
}