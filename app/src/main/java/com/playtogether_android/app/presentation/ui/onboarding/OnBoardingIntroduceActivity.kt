package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOnBoardingIntroduceBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import com.playtogether_android.app.util.CustomDialog
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.regex.Pattern

@AndroidEntryPoint
class OnBoardingIntroduceActivity :
    BaseActivity<ActivityOnBoardingIntroduceBinding>(R.layout.activity_on_boarding_introduce) {

    private val signViewModel: SignViewModel by viewModels()
    private val onBoardingViewModel: OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        nullCheck()
        nameTextWatcher()
        introTextWatcher()
        initSetting()
        duplicationClickEvent()
        nextBtnClickListener()
        subwayBtnListener()

    }

    private fun nextBtnClickListener() {
        binding.tvIntroOnboardingNext.setOnClickListener {
            val crewName = intent.getStringExtra("crewName")
            val crewCode = intent.getStringExtra("crewCode")
            val crewIntroduce = intent.getStringExtra("crewIntro")


            val name = binding.etIntroOnboardingName.text.toString()
            val intent = Intent(this, OpenCrewEndOnBoardingActivity::class.java)

            intent.putExtra("userName", name)
            intent.putExtra("crewName", crewName)
            intent.putExtra("crewCode", crewCode)
            intent.putExtra("crewIntro", crewIntroduce)
            startActivity(intent)
            finish()
        }
    }

    //id 중복체크
    private fun idDuplicationCheck() {
        val id = binding.etIntroOnboardingName.text.toString()
        signViewModel.postIdDuplication(
            IdDuplicationCheckItem(id)
        )

        signViewModel.idDuplicationCheck.observe(this) {
            if (it.isUser) {
                binding.tvIntroOnboardingWarn.visibility = View.VISIBLE
                binding.tvIntroOnboardingCondition.visibility = View.INVISIBLE
                binding.tvIntroOnboardingApprove.visibility = View.INVISIBLE
            } else {
                binding.tvIntroOnboardingApprove.visibility = View.VISIBLE
                binding.tvIntroOnboardingWarn.visibility = View.INVISIBLE
                binding.tvIntroOnboardingCondition.visibility = View.INVISIBLE
            }
        }

    }

    //아이디 정규식
    private fun isVaildRegistrationId() = with(binding) {
        if (!Pattern.matches("^[a-z|0-9|]{1,10}\$", etIntroOnboardingName.text.toString())) {
            tvSignupmainIdDuplication.isSelected = false
            Timber.d("정규식 맞지 않음")
        } else {
            tvSignupmainIdDuplication.isSelected = true
            Timber.d("정규식 맞지 않음")
        }
    }


    //중복확인 버튼 클릭
    private fun duplicationClickEvent() {
        binding.tvSignupmainIdDuplication.setOnClickListener {
            if (binding.tvSignupmainIdDuplication.isSelected) {
                idDuplicationCheck()
            }
        }
    }

    private fun initSetting() {
        val crewName = intent.getStringExtra("crewName")
        binding.tvIntroOnboardingCrewName.setText(crewName)
    }

    //뒤로가기 버튼 리스너
    private fun backBtnListener() {
        binding.ivIntroOnboardingBack.setOnClickListener {
            val intent = Intent(this, SelectOnboardingActivity::class.java)
            startActivity(intent)
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
                val name = p0.toString()
                if (name.isEmpty()) {
                    binding.tvSignupmainIdDuplication.isSelected = false
                    binding.tvSignupmainIdDuplication.isClickable = false
                    binding.tvIntroOnboardingCondition.setTextColor(Color.parseColor("#C5C5C5"))
                } else if (name.length > 10 || name.contains(" ")) {
                    binding.tvSignupmainIdDuplication.isSelected = false
                    binding.tvSignupmainIdDuplication.isClickable = false
                    binding.tvIntroOnboardingCondition.setTextColor(Color.parseColor("#FF0000"))
                } else {
                    binding.tvSignupmainIdDuplication.isSelected = true
                    binding.tvSignupmainIdDuplication.isClickable = true
                    binding.tvIntroOnboardingCondition.setTextColor(Color.parseColor("#C5C5C5"))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                val id = p0.toString()
                binding.tvSignupmainIdDuplication.setOnClickListener {
                        onBoardingViewModel.userId.value = p0.toString()
                }

                val userId = onBoardingViewModel.userId.value
                if(userId != etIntroOnboardingName.text.toString()) {
                    tvIntroOnboardingWarn.visibility = View.INVISIBLE
                    tvIntroOnboardingApprove.visibility = View.INVISIBLE
                }

                etIntroOnboardingName.isSelected = etIntroOnboardingName.text.toString() != ""
                initTextFieldCheck()
                //isVaildRegistrationId()
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

    private fun subwayBtnListener() {
        binding.tvOpenOnboardingAdd.setOnClickListener {
            val intent = Intent(this, SearchSubwayActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}