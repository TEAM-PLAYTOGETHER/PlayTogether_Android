//package com.playtogether_android.app.di
//
//import com.playtogether_android.data.datasource.home.HomeDataSource
//import com.playtogether_android.data.datasource.home.HomeDataSourceImpl
//import com.playtogether_android.data.datasource.light.LightDataSource
//import com.playtogether_android.data.datasource.light.LightDataSourceImpl
//import com.playtogether_android.data.datasource.message.*
//import com.playtogether_android.data.datasource.mypage.MyPageDataSource
//import com.playtogether_android.data.datasource.mypage.MyPageDataSourceImpl
//import com.playtogether_android.data.datasource.onboarding.OnBoardingDataSource
//import com.playtogether_android.data.datasource.onboarding.OnBoardingDataSourceImpl
//import com.playtogether_android.data.datasource.sign.SignDataSource
//import com.playtogether_android.data.datasource.sign.SignDataSourceImpl
//import com.playtogether_android.data.datasource.thunder.ThunderCreateDataSource
//import com.playtogether_android.data.datasource.thunder.ThunderCreateDataSourceImpl
//import com.playtogether_android.data.datasource.thunder.ThunderDataSource
//import com.playtogether_android.data.datasource.thunder.ThunderDataSourceImpl
//import org.koin.dsl.module
//
//val dataSourceModule = module {
//    // TODO: done
//
//    single<SignDataSource> { SignDataSourceImpl(get()) }
//    single<LightDataSource> { LightDataSourceImpl(get()) }
//    single<OnBoardingDataSource> { OnBoardingDataSourceImpl(get()) }
//    single<ThunderDataSource> { ThunderDataSourceImpl(get()) }
//    single<MessageDataSource> { MessageDataSourceImpl(get()) }
//    single<SendMessageDataSource> { SendMessageDataSourceImpl(get()) }
//    single<ChatDataSource> { ChatDataSourceImpl(get()) }
//    single<ThunderCreateDataSource> { ThunderCreateDataSourceImpl(get()) }
//    single<MyPageDataSource> { MyPageDataSourceImpl(get()) }
//    single<HomeDataSource> { HomeDataSourceImpl(get()) }
//}