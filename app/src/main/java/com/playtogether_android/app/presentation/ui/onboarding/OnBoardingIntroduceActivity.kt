package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.chip.Chip
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOnBoardingIntroduceBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class OnBoardingIntroduceActivity : BaseActivity<ActivityOnBoardingIntroduceBinding>(R.layout.activity_on_boarding_introduce) {

    private val signViewModel: SignViewModel by viewModels()
    private val chipList = java.util.ArrayList<String>()

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
        setChipBtn()

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
        tvSignupmainIdDuplication.isSelected =
            Pattern.matches("^[a-z|0-9|]{1,10}\$", etIntroOnboardingName.text.toString())
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

            }

            override fun afterTextChanged(p0: Editable?) {
                etIntroOnboardingName.isSelected = etIntroOnboardingName.text.toString() != ""
                initTextFieldCheck()
                isVaildRegistrationId()
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
            for (i: Int in 1..binding.chipMypage.childCount) {
                val chip: Chip = binding.chipMypage.getChildAt(i - 1) as Chip
                chipList.add(chip.text.toString())
            }
            val intent = Intent(this, SearchSubwayActivity::class.java)
            intent.putExtra("ChipList", chipList)
            startActivity(intent)
            finish()
        }
    }

    //칩버튼 관리
    private fun setChipBtn() {
        val list = intent.getStringArrayListExtra("ChipList")
        if(list?.size != null) {
            binding.clOpenOnboardingPltoSubway.visibility = View.INVISIBLE
            for(i in 0 until list.size) {
                val chip = Chip(binding.chipMypage.context).apply {
                    text = list[i]
                    setTextColor(getColorStateList(R.color.main_green))

                    isCloseIconVisible = true
                    setCloseIconResource(R.drawable.icn_exit)
                    setCloseIconTintResource(R.color.gray_999999)
                    chipBackgroundColor = getColorStateList(R.color.black)
                    setOnCloseIconClickListener {
                        binding.chipMypage.removeView(
                            this
                        )

                    }
                }
                binding.chipMypage.addView(chip)
            }
        } else {
            binding.clOpenOnboardingPltoSubway.visibility = View.VISIBLE
        }
    }

}