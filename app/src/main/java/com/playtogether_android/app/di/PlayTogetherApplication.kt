//package com.playtogether_android.app.di
//
//import android.app.Application
//import android.content.Context
//import androidx.databinding.ktx.BuildConfig
//import org.koin.android.ext.koin.androidContext
//import org.koin.android.ext.koin.androidLogger
//import org.koin.core.context.startKoin
//import org.koin.core.logger.Level
//
//class PlayTogetherApplication : Application() {
//    init {
//        instance = this
//    }
//
//    companion object {
//        var instance: PlayTogetherApplication? = null
//        fun context(): Context {
//            return instance!!.applicationContext
//        }
//    }
//
//    override fun onCreate() {
//        super.onCreate()
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
//    }
//}