package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOpenCrewOnBoardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OpenCrewOnBoardingActivity : BaseActivity<ActivityOpenCrewOnBoardingBinding>(R.layout.activity_open_crew_on_boarding) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backBtnListener()
    }

    private fun backBtnListener() {
        binding.ivOpenOnboardingBack.setOnClickListener {
            startActivity(Intent(this, SelectOnboardingActivity::class.java))
            finish()
        }
    }

}