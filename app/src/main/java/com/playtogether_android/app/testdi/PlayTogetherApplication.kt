package com.playtogether_android.app.testdi

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.playtogether_android.app.util.PixelRatio
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

//        startKoin {
//            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
//            androidContext(this@PlayTogetherApplication)
//            modules(viewModelModule)
//            modules(networkModule)
//            modules(dataSourceModule)
//            modules(repositoryModule)
//            modules(useCaseModule)
//            modules(apiModule)
//        }
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

