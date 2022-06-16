package com.playtogether_android.app.testdi

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.databinding.ktx.BuildConfig
import com.playtogether_android.app.util.PixelRatio
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

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

