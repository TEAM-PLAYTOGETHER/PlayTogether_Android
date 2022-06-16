package com.playtogether_android.app.testdi

import com.playtogether_android.data.api.home.HomeService
import com.playtogether_android.data.api.light.LightService
import com.playtogether_android.data.api.message.ChatService
import com.playtogether_android.data.api.message.MessageSendService
import com.playtogether_android.data.api.message.MessageService
import com.playtogether_android.data.api.mypage.MyPageService
import com.playtogether_android.data.api.onboarding.OnboardingService
import com.playtogether_android.data.api.sign.SignService
import com.playtogether_android.data.api.thunder.ThunderCreateService
import com.playtogether_android.data.api.thunder.ThunderService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideSignService(@RetrofitModule.GsonConverter retrofit: Retrofit): SignService {
        return retrofit.create(SignService::class.java)
    }

    @Provides
    @Singleton
    fun provideOnboardingService(@RetrofitModule.GsonConverter retrofit: Retrofit): OnboardingService {
        return retrofit.create(OnboardingService::class.java)
    }

    @Provides
    @Singleton
    fun provideMessageService(@RetrofitModule.GsonConverter retrofit: Retrofit): MessageService {
        return retrofit.create(MessageService::class.java)
    }

    @Provides
    @Singleton
    fun provideLightService(@RetrofitModule.GsonConverter retrofit: Retrofit): LightService {
        return retrofit.create(LightService::class.java)
    }

    @Provides
    @Singleton
    fun provideThunderService(@RetrofitModule.GsonConverter retrofit: Retrofit): ThunderService {
        return retrofit.create(ThunderService::class.java)
    }

    @Provides
    @Singleton
    fun provideMessageSendService(@RetrofitModule.GsonConverter retrofit: Retrofit): MessageSendService {
        return retrofit.create(MessageSendService::class.java)
    }

    @Provides
    @Singleton
    fun provideChatService(@RetrofitModule.GsonConverter retrofit: Retrofit): ChatService {
        return retrofit.create(ChatService::class.java)
    }

    @Provides
    @Singleton
    fun provideThunderCreateService(@RetrofitModule.GsonConverter retrofit: Retrofit): ThunderCreateService {
        return retrofit.create(ThunderCreateService::class.java)
    }

    @Provides
    @Singleton
    fun provideMyPageService(@RetrofitModule.GsonConverter retrofit: Retrofit): MyPageService {
        return retrofit.create(MyPageService::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeService(@RetrofitModule.GsonConverter retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

}