package com.playtogether_android.app.di

import com.playtogether_android.data.datasource.sign.SignDataSource
import com.playtogether_android.data.datasource.sign.SignDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<SignDataSource> { SignDataSourceImpl(get()) }
}