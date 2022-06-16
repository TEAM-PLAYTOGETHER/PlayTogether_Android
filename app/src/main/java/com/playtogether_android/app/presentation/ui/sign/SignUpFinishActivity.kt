package com.playtogether_android.app.presentation.ui.sign

import android.content.Intent
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySignUpFinishBinding
import com.playtogether_android.app.presentation.base.BaseActivity
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
        //val name = intent.getStringExtra("userName").toString()
        binding.tvSignupFinishNickname.text = intent.getStringExtra("userName").toString()
    }

    //화면 이동
    private fun initMove() {
        binding.tvSignupFinishStart.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}