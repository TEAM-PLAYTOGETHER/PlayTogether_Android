package com.playtogether_android.app.presentation.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOpenCrewEndOnBoardingBinding
import com.playtogether_android.app.databinding.ActivityOpenCrewOnBoardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class OpenCrewEndOnBoardingActivity : BaseActivity<ActivityOpenCrewEndOnBoardingBinding>(R.layout.activity_open_crew_end_on_boarding) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSetting()
    }

    private fun initSetting() {
        val crewName = intent.getStringExtra("crewName")
        val crewIntroduce = intent.getStringExtra("crewIntroduce")

        //binding.tvOpenEndOnboardingName.setText(crewName)
        binding.tvOpenEndOnboardingCrew.setText(crewName)
        binding.tvOpenEndOnboardingCrewName.setText(crewName)
        binding.tvOpenEndOnboardingIntroAnswer.setText(crewIntroduce)
    }
}