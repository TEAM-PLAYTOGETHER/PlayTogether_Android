package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySelectOnboardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.sign.SignInActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
        binding.ivSecondOnboardingBack.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }

    private fun initOpenBtnClickListener() = with(binding) {
        clSecondOnboardingOpen.setOnClickListener {
            clSecondOnboardingOpen.isSelected = true

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
                Timber.d("개설로 이동")
            } else if (tvSecondOnboardingNext.isSelected and clSecondOnboardingJoinSelect.isSelected) {
                startActivity(
                    Intent(
                        this@SelectOnboardingActivity,
                        JoinOnBoardingActivity::class.java
                    )
                )
                finish()
            }
        }

    }
}
