package com.playtogether_android.app.presentation.ui.sign

import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySignUpFinishBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class SignUpFinishActivity : BaseActivity<ActivitySignUpFinishBinding>(R.layout.activity_sign_up_finish) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initName()

    }

    private fun initName() {
        //val name = intent.getStringExtra("userName").toString()
        binding.tvSignupFinishNickname.setText(intent.getStringExtra("userName").toString())
    }
}