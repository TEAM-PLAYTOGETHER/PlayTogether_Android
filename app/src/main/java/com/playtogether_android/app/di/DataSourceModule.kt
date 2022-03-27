package com.playtogether_android.app.di

import com.playtogether_android.data.datasource.TestDataSource
import com.playtogether_android.data.datasource.TestDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<TestDataSource>{ TestDataSourceImpl(get()) }
}