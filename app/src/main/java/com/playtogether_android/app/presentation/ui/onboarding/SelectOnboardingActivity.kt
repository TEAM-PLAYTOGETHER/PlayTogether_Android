package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySelectOnboardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.login.LoginActivity
import com.playtogether_android.app.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectOnboardingActivity :
    BaseActivity<ActivitySelectOnboardingBinding>(R.layout.activity_select_onboarding) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBackBtn()
        initOpenBtnClickListener()
        initJoinBtnClickListener()
        movePage()
    }

    //뒤로가기 버튼 클릭 리스너
    private fun initBackBtn() {
        val case = intent.getIntExtra("case",1)
        binding.ivSecondOnboardingBack.setOnClickListener {
            if(case == 2) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        }
    }

    private fun initOpenBtnClickListener() = with(binding) {
        clSecondOnboardingOpen.setOnClickListener {
            clSecondOnboardingOpen.isSelected = true
            ivSecondOnboardingJoinSelect.visibility = View.INVISIBLE
            ivSecondOnboardingOpenSelect.visibility = View.VISIBLE

            if (clSecondOnboardingJoinSelect.isSelected) {
                clSecondOnboardingOpen.isSelected = true
                clSecondOnboardingJoinSelect.isSelected = false
            }
            tvSecondOnboardingNext.isSelected = true
        }
    }


    private fun initJoinBtnClickListener() = with(binding) {
        clSecondOnboardingJoinSelect.setOnClickListener {
            clSecondOnboardingJoinSelect.isSelected = true
            ivSecondOnboardingJoinSelect.visibility = View.VISIBLE
            ivSecondOnboardingOpenSelect.visibility = View.INVISIBLE

            if (clSecondOnboardingOpen.isSelected) {
                clSecondOnboardingJoinSelect.isSelected = true
                clSecondOnboardingOpen.isSelected = false
            }
            tvSecondOnboardingNext.isSelected = true
        }
    }


    private fun movePage() = with(binding) {
        tvSecondOnboardingNext.setOnClickListener {
            if (tvSecondOnboardingNext.isSelected and clSecondOnboardingOpen.isSelected) {
                val intent = Intent(this@SelectOnboardingActivity, OpenCrewOnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            } else if (tvSecondOnboardingNext.isSelected and clSecondOnboardingJoinSelect.isSelected) {
                val intent = Intent(this@SelectOnboardingActivity, JoinOnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
