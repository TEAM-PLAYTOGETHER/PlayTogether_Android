package com.playtogether_android.app.presentation.ui.login

import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityLoginBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
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

        }
    }

    private fun btnGoogleListener() {

    }


}