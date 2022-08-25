package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySignUpFinishBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.main.MainActivity
import com.playtogether_android.app.presentation.ui.sign.SignInActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFinishActivity :
    BaseActivity<ActivitySignUpFinishBinding>(R.layout.activity_sign_up_finish) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initName()
        initMove()

    }

    private fun initName() {
        val name = intent.getStringExtra("nickname").toString()
        binding.tvSignupFinishNickname.setText(name)
    }

    //화면 이동
    private fun initMove() {
        binding.tvSignupFinishStart.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}