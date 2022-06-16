//package com.playtogether_android.app.di
//
//import com.playtogether_android.data.api.home.HomeService
//import com.playtogether_android.data.api.light.LightService
//import com.playtogether_android.data.api.message.ChatService
//import com.playtogether_android.data.api.message.MessageSendService
//import com.playtogether_android.data.api.message.MessageService
//import com.playtogether_android.data.api.mypage.MyPageService
//import com.playtogether_android.data.api.onboarding.OnboardingService
//import com.playtogether_android.data.api.sign.SignService
//import com.playtogether_android.data.api.thunder.ThunderCreateService
//import com.playtogether_android.data.api.thunder.ThunderService
//import org.koin.dsl.module
//import retrofit2.Retrofit
//
//val networkModule = module {
//    // TODO: done
//
//    single<SignService> {
//        get<Retrofit>().create(SignService::class.java)
//    }
//
//    single<OnboardingService> {
//        get<Retrofit>().create(OnboardingService::class.java)
//    }
//
//    single<MessageService> {
//        get<Retrofit>().create(MessageService::class.java)
//    }
//    single<LightService> {
//        get<Retrofit>().create(LightService::class.java)
//    }
//
//    single<ThunderService> {
//        get<Retrofit>().create(ThunderService::class.java)
//    }
//
//    single<MessageSendService> {
//        get<Retrofit>().create(MessageSendService::class.java)
//    }
//
//    single<ChatService> {
//        get<Retrofit>().create(ChatService::class.java)
//    }
//
//    single<ThunderCreateService> {
//        get<Retrofit>().create(ThunderCreateService::class.java)
//    }
//
//    single<MyPageService> {
//        get<Retrofit>().create(MyPageService::class.java)
//    }
//
//    single<HomeService> {
//        get<Retrofit>().create(HomeService::class.java)
//    }
//}