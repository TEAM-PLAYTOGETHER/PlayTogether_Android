package com.playtogether_android.app.di

import com.playtogether_android.data.api.google_sign.GoogleService
import com.playtogether_android.data.api.home.HomeService
import com.playtogether_android.data.api.light.LightService
import com.playtogether_android.data.api.message.ChatService
import com.playtogether_android.data.api.message.MessageService
import com.playtogether_android.data.api.mypage.MyPageService
import com.playtogether_android.data.api.onboarding.OnboardingService
import com.playtogether_android.data.api.onboarding.SubwayInfoService
import com.playtogether_android.data.api.sign.SignService
import com.playtogether_android.data.api.thunder.ThunderCreateService
import com.playtogether_android.data.api.thunder.ThunderService
import com.playtogether_android.data.api.userInfo.UserInfoService
import com.playtogether_android.data.datasource.home.HomeDataSource
import com.playtogether_android.data.datasource.home.HomeDataSourceImpl
import com.playtogether_android.data.datasource.light.LightDataSource
import com.playtogether_android.data.datasource.light.LightDataSourceImpl
import com.playtogether_android.data.datasource.message.*
import com.playtogether_android.data.datasource.mypage.MyPageDataSource
import com.playtogether_android.data.datasource.mypage.MyPageDataSourceImpl
import com.playtogether_android.data.datasource.onboarding.OnBoardingDataSource
import com.playtogether_android.data.datasource.onboarding.OnBoardingDataSourceImpl
import com.playtogether_android.data.datasource.onboarding.SubwayDataSource
import com.playtogether_android.data.datasource.onboarding.SubwayDataSourceImpl
import com.playtogether_android.data.datasource.sign.SignDataSource
import com.playtogether_android.data.datasource.sign.SignDataSourceImpl
import com.playtogether_android.data.datasource.sign.google.GoogleDataSource
import com.playtogether_android.data.datasource.sign.google.GoogleDataSourceImpl
import com.playtogether_android.data.datasource.thunder.ThunderCreateDataSource
import com.playtogether_android.data.datasource.thunder.ThunderCreateDataSourceImpl
import com.playtogether_android.data.datasource.thunder.ThunderDataSource
import com.playtogether_android.data.datasource.thunder.ThunderDataSourceImpl
import com.playtogether_android.data.datasource.userInfo.UserInfoDataSource
import com.playtogether_android.data.datasource.userInfo.UserInfoDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideSubwayDataSource(service: SubwayInfoService): SubwayDataSource {
        return SubwayDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideGoogleService(service: GoogleService): GoogleDataSource {
        return GoogleDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideSignDataSource(service: SignService): SignDataSource {
        return SignDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideLightDataSource(service: LightService): LightDataSource {
        return LightDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideOnBoardingDataSource(service: OnboardingService): OnBoardingDataSource {
        return OnBoardingDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideThunderDataSource(service: ThunderService): ThunderDataSource {
        return ThunderDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMessageDataSource(service: MessageService): MessageDataSource {
        return MessageDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideChatDataSource(service: ChatService): ChatDataSource {
        return ChatDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideThunderCreateDataSource(service: ThunderCreateService): ThunderCreateDataSource {
        return ThunderCreateDataSourceImpl(service)
    }


    @Provides
    @Singleton
    fun provideMyPageDataSource(service: MyPageService): MyPageDataSource {
        return MyPageDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideHomeDataSource(service: HomeService): HomeDataSource {
        return HomeDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideUserInfoDataSource(service: UserInfoService): UserInfoDataSource {
        return UserInfoDataSourceImpl(service)
    }
}