package com.playtogether_android.app.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySplashBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.playtogether_android.app.presentation.ui.login.LoginActivity
import com.playtogether_android.app.presentation.ui.onboarding.OnboardingReDownLoadActivity
import com.playtogether_android.app.presentation.ui.onboarding.SelectOnboardingActivity
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import com.playtogether_android.data.singleton.PlayTogetherRepository
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val signViewModel: SignViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

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
            //todo 카카오 or 구글 로그 아웃한 경우
            else if (!userLogin) {
                moveLoginActivity()
            }
            //todo 카카오 자동 로그인
            else if (kakaoUserToken == userToken) {
                signViewModel.tokenChecker(userRefreshToken)
                accessTokenChecker()
                Timber.e("auto kakao login : 카카오 자동 로그인")
                Timber.e("auto login : $userToken")
            }
            //todo 구글 자동 로그인
            else if (googleUserToken == userToken) {
                signViewModel.tokenChecker(userRefreshToken)
                accessTokenChecker()
                Timber.e("auto google login : 구글 자동 로그인")
                Timber.e("auto login : $userToken")
            }
            // todo 아몰랑 예외
            else {
                Timber.e("auto else ")
                Timber.e("auto user : $userToken")
                Timber.e("auto user : $kakaoUserToken")
                Timber.e("auto user : $googleUserToken")
                moveLoginActivity()
            }
        }
    }

    private fun accessTokenChecker() {
        homeViewModel.getCrewList()
        val isEmpty = homeViewModel.isCrewListEmpty

        Timber.e("isEmpty : $isEmpty")
        signViewModel.statusCode.observe(this@SplashActivity) { status ->
            Timber.e("status : $status")
            when (status) {
                ACCESS_NOW, REFRESH_SUCCESS -> {
                    if (isEmpty && PlayTogetherRepository.crewId == -1)
                        moveSelectCrew()
                    else if (PlayTogetherRepository.crewId == -1 && isEmpty)
                        moveJoinOrCreateCrew()
                    else
                        moveMain()
                }
                else -> moveLoginActivity()
            }
        }
    }

    private fun moveSelectCrew() {
        Intent(this, OnboardingReDownLoadActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startNextActivityWithHandling(this)
        }
    }

    private fun moveJoinOrCreateCrew() {
        val intent = Intent(baseContext, SelectOnboardingActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startNextActivityWithHandling(intent)
    }

    companion object {
        private const val DURATION: Long = 2000
        const val REFRESH_SUCCESS = 200
        const val ACCESS_NOW = 400
    }

}