package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOpenCrewEndOnBoardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class OpenCrewEndOnBoardingActivity : BaseActivity<ActivityOpenCrewEndOnBoardingBinding>(R.layout.activity_open_crew_end_on_boarding) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSetting()
    }

    private fun initSetting() {
        val crewName = intent.getStringExtra("crewName")
        val crewIntroduce = intent.getStringExtra("crewIntroduce")
        val crewCode = intent.getStringExtra("crewCode")

        //binding.tvOpenEndOnboardingName.setText(crewName)
        binding.tvOpenEndOnboardingCrew.setText(crewName)
        binding.tvOpenEndOnboardingCrewName.setText(crewName)
        binding.tvOpenEndOnboardingIntroAnswer.setText(crewIntroduce)
        binding.tvOpenEndOnboardingCodeAnswer.setText(crewCode)

        binding.tvOpenOnboardingNext.setOnClickListener {
            val intent = Intent(this, OnBoardingIntroduceActivity::class.java)
            intent.putExtra("crewName", crewName)
            startActivity(intent)
            finish()
        }

        binding.tvOpenEndOnboardingShare.setOnClickListener{
/*
            val message = "crewName"
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.setPackage("com.kakao.talk")
            startActivity(intent)

 */
        }
    }

}