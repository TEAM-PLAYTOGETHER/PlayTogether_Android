package com.playtogether_android.app.di

import com.playtogether_android.data.datasource.sign.SignDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<SignDataSource>{get()}
}