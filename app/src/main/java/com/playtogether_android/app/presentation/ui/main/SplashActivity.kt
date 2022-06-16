package com.playtogether_android.app.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySplashBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.sign.SignInActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplash()
    }

    private fun initSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }, DURATION)

    }

    companion object {
        private const val DURATION: Long = 2000
    }

}