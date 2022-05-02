package com.playtogether_android.app.di

import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.playtogether_android.app.presentation.ui.main.viewmodel.MainViewModel
import com.playtogether_android.app.presentation.ui.sign.SignUpMainActivity
import com.playtogether_android.app.presentation.ui.sign.SignViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    //main
    viewModel {
        MainViewModel()
        HomeViewModel()
    }

    //sign
    viewModel {SignViewModel()}

}


