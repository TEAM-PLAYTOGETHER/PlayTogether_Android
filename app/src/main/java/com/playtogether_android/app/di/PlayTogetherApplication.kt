package com.playtogether_android.app.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.kakao.sdk.common.KakaoSdk
import com.playtogether_android.app.BuildConfig
import com.playtogether_android.app.util.PixelRatio
import com.playtogether_android.data.singleton.PlayTogetherRepository
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PlayTogetherApplication : Application(), Application.ActivityLifecycleCallbacks {
    init {
        instance = this
    }

    companion object {
        var instance: PlayTogetherApplication? = null
        fun context(): Context {
            return instance!!.applicationContext
        }

        lateinit var pixelRatio: PixelRatio
        var isBackground = false
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        initLogger()
        PlayTogetherRepository.init(this)
        initKakaoLogin()
        getDeviceToken()
        initPixelUtil()
    }
    private fun initPixelUtil() {
        pixelRatio = PixelRatio(this)
    }
    private fun getDeviceToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val token = task.result
            PlayTogetherRepository.fireBaseToken = token.toString()
            Timber.d("token firebase : ${PlayTogetherRepository.fireBaseToken}")
        })
    }

    private fun initKakaoLogin() {
        val kakaoAppKey = BuildConfig.KAKAOKEY
        KakaoSdk.init(this, kakaoAppKey)
    }

    override fun onTerminate() {
        super.onTerminate()
        unregisterActivityLifecycleCallbacks(this)
    }

    private fun initLogger() {
        Timber.plant(Timber.DebugTree())
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {

    }

    override fun onActivityStarted(p0: Activity) {
        isBackground = false
    }

    override fun onActivityResumed(p0: Activity) {
        isBackground = false
    }

    override fun onActivityPaused(p0: Activity) {
        isBackground = true
    }

    override fun onActivityStopped(p0: Activity) {

    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

    }

    override fun onActivityDestroyed(p0: Activity) {

    }
}

