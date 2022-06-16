package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityFirstOnBoardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstOnBoardingActivity :
    BaseActivity<ActivityFirstOnBoardingBinding>(R.layout.activity_first_on_boarding) {

    private val onBoardingViewModel: OnBoardingViewModel by viewModels()
    private val signInViewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSelectMbti()
        actvieNextBtn()
        moveNextBtn()
        settingName()
    }

    //라디오 버튼 클릭 리스너
    private fun initSelectMbti() = with(binding) {
        rgFirstOnboardingFirstMbti.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.radio_I -> {

                }
                R.id.radio_E -> {

                }
            }
            onBoardingViewModel.firstMbtiClick.value = true
        }

        rgFirstOnboardingSecondMbti.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_N -> {

                }
                R.id.radio_S -> {

                }
            }
            onBoardingViewModel.secondMbtiClick.value = true
        }

        rgFirstOnboardingThirdMbti.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_F -> {

                }
                R.id.radio_T -> {

                }
            }
            onBoardingViewModel.thirdMbtiClick.value = true
        }

        rgFirstOnboardingForthMbti.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_J -> {

                }
                R.id.radio_P -> {

                }
            }
            onBoardingViewModel.forthMbtiClick.value = true
        }

    }

    //이름세팅
    private fun settingName() {
        val name = intent.getStringExtra("userName").toString()
        binding.tvFirstOnboardingName.text = name
    }

    //다음 버튼 활성화
    private fun actvieNextBtn() {
        onBoardingViewModel.selectedAll.observe(this) {
            binding.tvFirstOnboardingNext.isSelected = it
            if (binding.tvFirstOnboardingNext.isSelected) {
                binding.tvFirstOnboardingNext.visibility = View.INVISIBLE
                binding.tvFirstOnboardingStart.visibility = View.VISIBLE
            }
        }
    }

    //다음 온보딩으로 넘어갈 수 있는 btn들
    private fun moveNextBtn() {
        binding.tvFirstOnboardingStart.setOnClickListener {
            startActivity(Intent(this, SecondOnboardingActivity::class.java))
            finish()
        }

        binding.tvFirstOnboardingRelevant.setOnClickListener {
            startActivity(Intent(this, SecondOnboardingActivity::class.java))
            finish()
        }
    }


}