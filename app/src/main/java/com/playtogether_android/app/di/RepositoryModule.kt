//package com.playtogether_android.app.di
//
//import com.playtogether_android.data.repositoryimpl.home.HomeRepositoryImpl
//import com.playtogether_android.data.repositoryimpl.light.LightRepositoryImpl
//import com.playtogether_android.data.repositoryimpl.message.ChatRepositoryImpl
//import com.playtogether_android.data.repositoryimpl.message.MessageRepositoryImpl
//import com.playtogether_android.data.repositoryimpl.message.SendMessageRepositoryImpl
//import com.playtogether_android.data.repositoryimpl.mypage.MyPageRepositoryImpl
//import com.playtogether_android.data.repositoryimpl.onboarding.OnBoardingRepositoryImpl
//import com.playtogether_android.data.repositoryimpl.sign.SignRepositoryImpl
//import com.playtogether_android.data.repositoryimpl.thunder.ThunderCreateRepositoryImpl
//import com.playtogether_android.data.repositoryimpl.thunder.ThunderRepositoryImpl
//import com.playtogether_android.domain.repository.home.HomeRepository
//import com.playtogether_android.domain.repository.light.LightRepository
//import com.playtogether_android.domain.repository.message.ChatRepository
//import com.playtogether_android.domain.repository.message.MessageRepository
//import com.playtogether_android.domain.repository.message.MessageSendReposiotry
//import com.playtogether_android.domain.repository.mypage.MyPageRepository
//import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository
//import com.playtogether_android.domain.repository.sign.SignRepository
//import com.playtogether_android.domain.repository.thunder.ThunderCreateRepository
//import com.playtogether_android.domain.repository.thunder.ThunderRepository
//import org.koin.dsl.module
//
//val repositoryModule = module {
//    // TODO: done
//    single<SignRepository> { SignRepositoryImpl(get()) }
//    single<LightRepository> { LightRepositoryImpl(get()) }
////    single<SignRepository>{ SignRepositoryImpl(get()) }
//
//    single<OnBoardingRepository> { OnBoardingRepositoryImpl(get()) }
//
//    single<MessageRepository> { MessageRepositoryImpl(get()) }
//    single<ThunderRepository> { ThunderRepositoryImpl(get()) }
//    single<MessageSendReposiotry> { SendMessageRepositoryImpl(get()) }
//    single<ChatRepository> { ChatRepositoryImpl(get()) }
//
//    single<MyPageRepository> { MyPageRepositoryImpl(get()) }
//    single<HomeRepository> { HomeRepositoryImpl(get()) }
//    single<ThunderCreateRepository> { ThunderCreateRepositoryImpl(get()) }
//}
