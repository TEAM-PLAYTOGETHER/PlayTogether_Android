package com.playtogether_android.app.di

import com.playtogether_android.app.presentation.ui.main.TestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TestViewModel(get()) }
}


