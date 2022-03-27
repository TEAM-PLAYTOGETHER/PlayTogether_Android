package com.playtogether_android.app.di

import com.playtogether_android.data.repositoryimpl.TestRepositoryImpl
import com.playtogether_android.domain.repository.TestRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<TestRepository>{ TestRepositoryImpl(get()) }
}
