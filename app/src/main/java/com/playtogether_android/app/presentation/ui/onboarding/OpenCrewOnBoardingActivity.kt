package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOpenCrewOnBoardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.domain.model.onboarding.CrewItem
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class OpenCrewOnBoardingActivity :
    BaseActivity<ActivityOpenCrewOnBoardingBinding>(R.layout.activity_open_crew_on_boarding) {

    private val onBoardingViewModel: OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backBtnListener()
        nullCheck()
        nameTextWatcher()
        introTextWatcher()
        nextBtnClickListener()
    }

    //뒤로가기 버튼 리스너
    private fun backBtnListener() {
        binding.ivOpenOnboardingBack.setOnClickListener {
            startActivity(Intent(this, SelectOnboardingActivity::class.java))
            finish()
        }
    }

    //et 비었는지 체크
    private fun nullCheck() {
        binding.etOpenOnboardingName.setOnClickListener {
            initTextFieldCheck()
        }

        binding.etOpenOnboardingIntro.setOnClickListener {
            initTextFieldCheck()
        }
    }

    //backgroundresource 변경
    private fun initTextFieldCheck() {
        if (binding.etOpenOnboardingName.text.toString() != "") {
            binding.etOpenOnboardingName.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
        } else {
            binding.etOpenOnboardingName.setBackgroundResource(R.drawable.selector_rectangle_border_gray03_to_black02)
        }

        if (binding.etOpenOnboardingIntro.text.toString() != "") {
            binding.etOpenOnboardingIntro.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
        } else {
            binding.etOpenOnboardingIntro.setBackgroundResource(R.drawable.selector_rectangle_border_gray03_to_black02)
        }

    }

    //이름 textWatcher
    private fun nameTextWatcher() = with(binding) {
        etOpenOnboardingName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val name = p0.toString()
                if (!Pattern.matches("^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z|A-Z|0-9|]{1,15}\$", name)) {
                    binding.tvOpenOnboardingApprove.visibility = View.INVISIBLE
                    binding.tvOpenOnboardingWarn.visibility = View.VISIBLE
                } else {
                    binding.tvOpenOnboardingApprove.visibility = View.VISIBLE
                    binding.tvOpenOnboardingWarn.visibility = View.INVISIBLE

                }
                initBtnActive()
            }

            override fun afterTextChanged(p0: Editable?) {
                etOpenOnboardingName.isSelected = etOpenOnboardingName.text.toString() != ""
                initTextFieldCheck()
                initBtnActive()

            }
        })
    }

    //소개 textWatcher
    private fun introTextWatcher() = with(binding) {
        etOpenOnboardingIntro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                etOpenOnboardingIntro.isSelected = etOpenOnboardingIntro.text.toString() != ""
                initTextFieldCheck()
                initBtnActive()
            }
        })
    }

    private fun initBtnActive() {
        binding.tvOpenOnboardingNext.isSelected =
            (binding.tvOpenOnboardingApprove.isVisible) && binding.etOpenOnboardingIntro.text.toString() != ""
    }

    private fun nextBtnClickListener() {
        binding.tvOpenOnboardingNext.setOnClickListener {
            if (binding.tvOpenOnboardingNext.isSelected) {
                onBoardingViewModel.requestCew.crewName = binding.etOpenOnboardingName.text.toString()
                onBoardingViewModel.requestCew.description = binding.etOpenOnboardingIntro.text.toString()
                onBoardingViewModel.postCrew(
                    CrewItem(
                        onBoardingViewModel.requestCew.crewName,
                        onBoardingViewModel.requestCew.description
                    )
                )

                onBoardingViewModel.crew.observe(this) {
                    var code = it.code
                    Log.d("Test", code.toString())
                    val intent = Intent(this, OpenCrewEndOnBoardingActivity::class.java)
                    intent.putExtra("crewName", binding.etOpenOnboardingName.text.toString())
                    intent.putExtra("crewIntroduce", binding.etOpenOnboardingIntro.text.toString())
                    intent.putExtra("crewCode", code)
                    startActivity(intent)
                    finish()
                }



            }
        }
    }
}
