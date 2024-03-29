package com.playtogether_android.app.di

import com.playtogether_android.data.api.message.RoomIdService
import com.playtogether_android.data.api.search.SearchService
import com.playtogether_android.data.datasource.home.HomeDataSource
import com.playtogether_android.data.datasource.light.LightDataSource
import com.playtogether_android.data.datasource.message.ChatDataSource
import com.playtogether_android.data.datasource.message.MessageDataSource
import com.playtogether_android.data.datasource.mypage.MyPageDataSource
import com.playtogether_android.data.datasource.onboarding.OnBoardingDataSource
import com.playtogether_android.data.datasource.onboarding.SubwayDataSource
import com.playtogether_android.data.datasource.sign.SignDataSource
import com.playtogether_android.data.datasource.sign.google.GoogleDataSource
import com.playtogether_android.data.datasource.thunder.ThunderCreateDataSource
import com.playtogether_android.data.datasource.thunder.ThunderDataSource
import com.playtogether_android.data.datasource.userInfo.UserInfoDataSource
import com.playtogether_android.data.repositoryimpl.home.HomeRepositoryImpl
import com.playtogether_android.data.repositoryimpl.light.LightRepositoryImpl
import com.playtogether_android.data.repositoryimpl.message.ChatRepositoryImpl
import com.playtogether_android.data.repositoryimpl.message.MessageRepositoryImpl
import com.playtogether_android.data.repositoryimpl.message.RoomIdRepositoryImpl
import com.playtogether_android.data.repositoryimpl.mypage.MyPageRepositoryImpl
import com.playtogether_android.data.repositoryimpl.onboarding.OnBoardingRepositoryImpl
import com.playtogether_android.data.repositoryimpl.onboarding.SubwayRepositoryImpl
import com.playtogether_android.data.repositoryimpl.search.SearchRepositoryImpl
import com.playtogether_android.data.repositoryimpl.sign.SignRepositoryImpl
import com.playtogether_android.data.repositoryimpl.sign.google.GoogleRepositoryImpl
import com.playtogether_android.data.repositoryimpl.thunder.ThunderCreateRepositoryImpl
import com.playtogether_android.data.repositoryimpl.thunder.ThunderRepositoryImpl
import com.playtogether_android.data.repositoryimpl.userInfo.UserInfoRepositoryImpl
import com.playtogether_android.domain.repository.home.HomeRepository
import com.playtogether_android.domain.repository.light.LightRepository
import com.playtogether_android.domain.repository.message.ChatRepository
import com.playtogether_android.domain.repository.message.MessageRepository
import com.playtogether_android.domain.repository.message.RoomIdRepository
import com.playtogether_android.domain.repository.mypage.MyPageRepository
import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository
import com.playtogether_android.domain.repository.onboarding.SubwayRepository
import com.playtogether_android.domain.repository.search.SearchRepository
import com.playtogether_android.domain.repository.sign.SignRepository
import com.playtogether_android.domain.repository.sign.google.GoogleRepository
import com.playtogether_android.domain.repository.thunder.ThunderCreateRepository
import com.playtogether_android.domain.repository.thunder.ThunderRepository
import com.playtogether_android.domain.repository.userInfo.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSignRepository(dataSource: SignDataSource): SignRepository {
        return SignRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideLightRepository(dataSource: LightDataSource): LightRepository {
        return LightRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideOnBoardingRepository(dataSource: OnBoardingDataSource): OnBoardingRepository {
        return OnBoardingRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideSubwayRepository(dataSource: SubwayDataSource) : SubwayRepository {
        return SubwayRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideGoogleRepository(dataSource: GoogleDataSource) : GoogleRepository {
        return GoogleRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideMessageRepository(dataSource: MessageDataSource): MessageRepository {
        return MessageRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideThunderRepository(dataSource: ThunderDataSource): ThunderRepository {
        return ThunderRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideChatRepository(dataSource: ChatDataSource): ChatRepository {
        return ChatRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideRoomIdRepository(service : RoomIdService):RoomIdRepository{
        return RoomIdRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(service : SearchService): SearchRepository {
        return SearchRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideMyPageRepository(dataSource: MyPageDataSource): MyPageRepository {
        return MyPageRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(dataSource: HomeDataSource): HomeRepository {
        return HomeRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideThunderCreateRepository(dataSource: ThunderCreateDataSource): ThunderCreateRepository {
        return ThunderCreateRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideUserInfoRepository(dataSource: UserInfoDataSource): UserInfoRepository {
        return UserInfoRepositoryImpl(dataSource)
    }
}