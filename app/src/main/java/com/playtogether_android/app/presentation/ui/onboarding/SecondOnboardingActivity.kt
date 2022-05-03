package com.playtogether_android.app.presentation.ui.onboarding

import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySecondOnboardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class SecondOnboardingActivity : BaseActivity<ActivitySecondOnboardingBinding>(R.layout.activity_second_onboarding) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initClickListener()
    }

    private fun initClickListener() = with(binding){

    }
}