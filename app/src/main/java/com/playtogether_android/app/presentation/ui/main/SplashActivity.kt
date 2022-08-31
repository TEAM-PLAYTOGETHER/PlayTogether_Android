package com.playtogether_android.app.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySplashBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.login.LoginActivity
import com.playtogether_android.app.presentation.ui.sign.SignInActivity
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import com.playtogether_android.data.singleton.PlayTogetherRepository
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.math.sign

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val signViewModel: SignViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
//        initSplash()
    }

    private fun initView() {
        appUpdateChecker()
        autoLoginCheck()
    }

    private fun initSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }, DURATION)
    }

    //업데이트 버전 체크
    private fun appUpdateChecker() {}

    private fun moveLoginActivity() {
        Intent(this, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startNextActivityWithHandling(this)
        }
    }

    private fun startNextActivityWithHandling(intent: Intent) {
        Handler(Looper.getMainLooper())
            .postDelayed({
                startActivity(intent)
                finish()
            }, 1000)
    }

    private fun moveMain() {
        val intent = Intent(baseContext, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startNextActivityWithHandling(intent)
    }

    private fun autoLoginCheck() {
        with(PlayTogetherRepository) {
            // todo  회원가입 유도
            if (kakaoAccessToken.isEmpty() && googleAccessToken.isEmpty()) {
                Timber.e("splash auto login : 회원 가입 안함")
                moveLoginActivity()
            }
            //todo 카카오 자동 로그인
            else if (kakaoUserToken == userToken) {
                signViewModel.tokenChecker(kakaoAccessToken, kakaoUserRefreshToken)
                tokenChecker()
                Timber.e("splash auto kakao login : 카카오 자동 로그인")
            }
            //todo 구글 자동 로그인
            else if (googleAccessToken == userToken) {
                signViewModel.tokenChecker(googleAccessToken, googleUserRefreshToken)
                tokenChecker()
                Timber.e("splash auto google login : 구글 자동 로그인")
            }
            //todo 카카오 or 구글 로그 아웃한 경우
            else if (kakaoUserlogOut || googleUserlogOut) {
                moveLoginActivity()
            }
            // todo 아몰랑 예외
            else {
                moveLoginActivity()
            }
        }
    }

    private fun tokenChecker() {
        signViewModel.statusCode.observe(this@SplashActivity) { status ->
            when (status) {
                ACCESS_NOW, REFRESH_SUCCESS -> {
                    moveMain()
                }
                else -> moveLoginActivity()
            }
        }
    }

    companion object {
        private const val DURATION: Long = 2000
        const val REFRESH_SUCCESS = 200
        const val ACCESS_NOW = 400
    }

}