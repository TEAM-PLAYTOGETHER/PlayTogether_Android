package com.playtogether_android.app.di

import com.playtogether_android.app.BuildConfig.BASE_URL
import com.playtogether_android.app.BuildConfig.SUBWAY_URL
import com.playtogether_android.app.util.AuthInterceptor
import com.playtogether_android.app.util.PlayTogetherSharedPreference
import com.playtogether_android.data.api.home.HomeService
import com.playtogether_android.data.api.light.LightService
import com.playtogether_android.data.api.message.ChatService
import com.playtogether_android.data.api.message.MessageService
import com.playtogether_android.data.api.message.RoomIdService
import com.playtogether_android.data.api.mypage.MyPageService
import com.playtogether_android.data.api.onboarding.OnboardingService
import com.playtogether_android.data.api.onboarding.SubwayInfoService
import com.playtogether_android.data.api.search.SearchService
import com.playtogether_android.data.api.sign.SignService
import com.playtogether_android.data.api.thunder.ThunderCreateService
import com.playtogether_android.data.api.thunder.ThunderService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideSubwayService() : SubwayInfoService {
        return Retrofit.Builder()
            .baseUrl(SUBWAY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SubwayInfoService::class.java)
    }

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
    fun provideRoomIdService(@RetrofitModule.GsonConverter retrofit: Retrofit) : RoomIdService{
        return retrofit.create(RoomIdService::class.java)
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

    @Provides
    @Singleton
    fun provideSearchService(@RetrofitModule.GsonConverter retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .run {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(provideInterceptor())
                addInterceptor(AuthInterceptor())
                build()
            }

    @Singleton
    @Provides
    fun provideInterceptor() =
        Interceptor { chain ->
            with(chain) {
                val newRequest = request().newBuilder()
                    .addHeader("Authorization",
                        PlayTogetherSharedPreference.getAccessToken(PlayTogetherApplication.context()))
                    .addHeader("Content-Type", "application/json")
                    .build()
                proceed(newRequest)
            }
        }
}