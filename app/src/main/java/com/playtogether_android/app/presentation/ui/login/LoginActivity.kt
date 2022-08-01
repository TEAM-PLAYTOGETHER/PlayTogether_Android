package com.playtogether_android.app.presentation.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityLoginBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val signViewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        onClickListener()
    }

    private fun onClickListener() {
        btnKakaoListener()
        btnGoogleListener()
    }

    private fun btnKakaoListener() {
        binding.ivLoginKakao.setOnClickListener {
//            setKakaoBtnListener()
        }
    }

    private fun btnGoogleListener() {

    }

//    private fun setKakaoBtnListener() {
//        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
//            if (error != null) {
//            } else if (token != null) {
//                UserApiClient.instance.me { _, error ->
//                    val accessToken = token.accessToken
//                    //토큰저장 후 api콜백
////                    CardNaRepository.kakaoAccessToken = accessToken
//                    Timber.d("login access token : ${accessToken}")
//                    with(signViewModel) {
//                        getKakaoLogin()
//                        isLogin.observe(this@LoginActivity) { success ->
//                            if (success) startMainActivity()
//                            else startSetNameActivity()
//                            Timber.d("isLogin : ${isLogin.value}")
//                            finish()
//                        }
//                    }
//                }
//            }
//        }
//
//        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
//            UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity, callback = callback)
//
//        } else {
//            UserApiClient.instance.loginWithKakaoAccount(
//                this@LoginActivity,
//                callback = callback
//            )
//        }
//    }
}