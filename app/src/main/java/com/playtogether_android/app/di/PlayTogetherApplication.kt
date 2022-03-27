package com.playtogether_android.app.di

import android.app.Application
import android.content.Context
import com.playtogether_android.BuildConfig
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class PlayTogetherApplication : Application() {
    init {
        instance = this
    }

    companion object {
        var instance: PlayTogetherApplication? = null
        fun context(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        }
    }
}