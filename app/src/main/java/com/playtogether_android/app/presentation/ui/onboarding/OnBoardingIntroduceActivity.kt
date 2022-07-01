package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOnBoardingIntroduceBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingIntroduceActivity : BaseActivity<ActivityOnBoardingIntroduceBinding>(R.layout.activity_on_boarding_introduce) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        nullCheck()
        nameTextWatcher()
        introTextWatcher()

    }

    //뒤로가기 버튼 리스너
    private fun backBtnListener() {
        binding.ivIntroOnboardingBack.setOnClickListener {
            startActivity(Intent(this, SelectOnboardingActivity::class.java))
            finish()
        }
    }

    //et 비었는지 체크
    private fun nullCheck() {
        binding.etIntroOnboardingName.setOnClickListener {
            initTextFieldCheck()
        }

        binding.etIntroOnboardingIntro.setOnClickListener {
            initTextFieldCheck()
        }
    }

    //backgroundresource 변경
    private fun initTextFieldCheck() {
        if (binding.etIntroOnboardingName.text.toString() != "") {
            binding.etIntroOnboardingName.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
        } else {
            binding.etIntroOnboardingName.setBackgroundResource(R.drawable.selector_rectangle_border_gray03_to_black02)
        }

        if (binding.etIntroOnboardingIntro.text.toString() != "") {
            binding.etIntroOnboardingIntro.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
        } else {
            binding.etIntroOnboardingIntro.setBackgroundResource(R.drawable.selector_rectangle_border_gray03_to_black02)
        }
    }

    //닉네임 textWatcher
    private fun nameTextWatcher() = with(binding) {
        etIntroOnboardingName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                etIntroOnboardingName.isSelected = etIntroOnboardingName.text.toString() != ""
                initTextFieldCheck()
            }
        })
    }

    //간단소개 textWatcher
    private fun introTextWatcher() = with(binding) {
        etIntroOnboardingIntro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                etIntroOnboardingIntro.isSelected = etIntroOnboardingIntro.text.toString() != ""
                initTextFieldCheck()
            }
        })
    }

}