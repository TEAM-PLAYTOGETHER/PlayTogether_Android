//package com.playtogether_android.app.di
//
//import com.playtogether_android.app.presentation.ui.createThunder.CreateThunderViewModel
//import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel
//import com.playtogether_android.app.presentation.ui.main.viewmodel.MainViewModel
//import com.playtogether_android.app.presentation.ui.message.viewmodel.ChatViewModel
//import com.playtogether_android.app.presentation.ui.message.viewmodel.MessageViewModel
//import com.playtogether_android.app.presentation.ui.message.viewmodel.SendMessageViewModel
//import com.playtogether_android.app.presentation.ui.mypage.viewModel.MyPageViewModel
//import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
//import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
//import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
//import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderDetailViewModel
//import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderViewModel
//import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.dsl.module
//
//val viewModelModule = module {
//
//    //main
//    viewModel {
//        MainViewModel()
//        HomeViewModel(get(),get(),get(),get())
//    }
//
//    //sign
//    viewModel { SignViewModel(get(), get(), get()) }
//
//    //onboarding
//    viewModel { OnBoardingViewModel(get()) }
//
//    //message
//    viewModel { MessageViewModel(get()) }
//    viewModel { SendMessageViewModel(get()) }
//    viewModel { ChatViewModel(get()) }
//
//    //ThunderList
//    viewModel { ThunderListViewModel(get()) }
//
//    //thunder
//    viewModel { ThunderViewModel(get(), get(), get()) }
//    viewModel { CreateThunderViewModel(get()) }
//
////    ThunderDetail
//    viewModel { ThunderDetailViewModel(get(), get(), get(), get(), get()) }
//
//    viewModel { MyPageViewModel(get()) }
//
//
//}
//
//
//
