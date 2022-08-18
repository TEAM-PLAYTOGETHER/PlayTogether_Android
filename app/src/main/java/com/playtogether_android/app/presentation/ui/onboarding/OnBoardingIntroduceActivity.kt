package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.chip.Chip
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOnBoardingIntroduceBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.domain.model.onboarding.AddProfileItem
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.regex.Pattern

@AndroidEntryPoint
class OnBoardingIntroduceActivity :
    BaseActivity<ActivityOnBoardingIntroduceBinding>(R.layout.activity_on_boarding_introduce) {

    private val signViewModel: SignViewModel by viewModels()
    private val onBoardingViewModel: OnBoardingViewModel by viewModels()
    private val chipList = java.util.ArrayList<String>()
    private lateinit var firstSubway: String
    private lateinit var secondSubway: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backBtnListener()
        nameTextWatcher()
        introTextWatcher()
        initSetting()
        nextBtnClickListener()
        subwayBtnListener()
        setChipBtn()
        nullCheck()
    }

    override fun onResume() {
        super.onResume()
        if (binding.etIntroOnboardingName.text.toString() != "") {
            nicknameDuplicationCheck()
        }
    }

    private fun nextBtnClickListener() {
        binding.tvIntroOnboardingNext.setOnClickListener {
            if (binding.tvIntroOnboardingNext.isSelected) {
                val crewName = intent.getStringExtra("crewName")
                val crewCode = intent.getStringExtra("crewCode")
                val crewIntroduce = intent.getStringExtra("crewIntro")


                val name = binding.etIntroOnboardingName.text.toString()
                val intent = Intent(this, OpenCrewEndOnBoardingActivity::class.java)

                intent.putExtra("userName", name)
                intent.putExtra("crewName", crewName)
                intent.putExtra("crewCode", crewCode)
                intent.putExtra("crewIntro", crewIntroduce)


                val nickName = binding.etIntroOnboardingName.text.toString()
                val description = binding.etIntroOnboardingIntro.text.toString()

                val list = intent.getStringArrayListExtra("ChipList")
                if (list?.size != null) {
                    for (i in 0 until list.size) {
                        val chip = Chip(binding.chipMypage.context).apply {
                            text = list[i]

                        }
                        //firstSubway = text
                    }

                }

                Timber.e("1 : $nickName")
                Timber.e("2 : $description")


                //onBoardingViewModel.putAddProfile(AddProfileItem())

                startActivity(intent)
                finish()
            }

        }
    }

    private fun nextBtnActive() {
        binding.tvIntroOnboardingNext.isSelected =
            binding.tvIntroOnboardingApprove.visibility == View.VISIBLE && binding.etIntroOnboardingIntro.text.toString() != ""
    }


    //중복확인 버튼 클릭
    private fun duplicationClickEvent() {
        binding.tvSignupmainIdDuplication.setOnClickListener {
            if (binding.tvSignupmainIdDuplication.isSelected) {
                nicknameDuplicationCheck()
            }
        }
    }

    private fun initSetting() {
        val crewName = intent.getStringExtra("crewName")
        val nickname = intent.getStringExtra("nickname")
        val description = intent.getStringExtra("description")
        binding.tvIntroOnboardingCrewName.setText(crewName)

        binding.etIntroOnboardingName.setText(nickname)
        binding.etIntroOnboardingIntro.setText(description)

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

                if (!Pattern.matches("^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z|A-Z|0-9|0-9|_]{2,10}\$", name)) {
                    binding.tvSignupmainIdDuplication.isSelected = false
                    binding.tvSignupmainIdDuplication.isClickable = false
                    binding.tvIntroOnboardingCondition.setTextColor(Color.parseColor("#FF0000"))
                } else if (name.isEmpty()) {
                    binding.tvSignupmainIdDuplication.isSelected = false
                    binding.tvSignupmainIdDuplication.isClickable = false
                    binding.tvIntroOnboardingCondition.setTextColor(Color.parseColor("#C5C5C5"))
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
                if (userId != etIntroOnboardingName.text.toString()) {
                    tvIntroOnboardingWarn.visibility = View.INVISIBLE
                    tvIntroOnboardingApprove.visibility = View.INVISIBLE
                }

                etIntroOnboardingName.isSelected = etIntroOnboardingName.text.toString() != ""
                initTextFieldCheck()
                duplicationClickEvent()
                nextBtnActive()

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
                nextBtnActive()
            }
        })
    }

    private fun subwayBtnListener() {
        binding.tvOpenOnboardingAdd.setOnClickListener {
            if (binding.tvOpenOnboardingAdd.isSelected) {
                shortToast("최대 2개까지 추가할 수 있어요!")
            } else {
                for (i: Int in 1..binding.chipMypage.childCount) {
                    val chip: Chip = binding.chipMypage.getChildAt(i - 1) as Chip
                    chipList.add(chip.text.toString())
                }
                val nickname = binding.etIntroOnboardingName.text.toString()
                val description = binding.etIntroOnboardingIntro.text.toString()
                val intent = Intent(this, SearchSubwayActivity::class.java)
                intent.putExtra("ChipList", chipList)
                intent.putExtra("nickname", nickname)
                intent.putExtra("description", description)
                startActivity(intent)
                finish()
            }


        }
    }


    //칩버튼 관리
    private fun setChipBtn() {
        val list = intent.getStringArrayListExtra("ChipList")
        if (list?.size != null) {
            binding.clOpenOnboardingPltoSubway.visibility = View.INVISIBLE
            for (i in 0 until list.size) {
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
                        addBtnListener()

                    }
                }
                binding.chipMypage.addView(chip)
                addBtnListener()
            }
        } else {
            binding.clOpenOnboardingPltoSubway.visibility = View.VISIBLE
        }
    }


    //칩버튼 추가하기
    private fun addBtnListener() {
        binding.tvOpenOnboardingAdd.isSelected = binding.chipMypage.childCount == 2

        if (binding.chipMypage.childCount == 0) {
            binding.tvOnboardingPlto.visibility = View.VISIBLE
        } else {
            binding.tvOnboardingPlto.visibility = View.INVISIBLE
        }
    }

    private fun nicknameDuplicationCheck() {
        val nickname: String = binding.etIntroOnboardingName.text.toString()

        //TODO : crewId 고정값 취소
        onBoardingViewModel.getNickNameDuplication(1, "$nickname")
        onBoardingViewModel.nicknameDuplicationCheck.observe(this) {
            if (!it.success) {
                binding.tvIntroOnboardingApprove.visibility = View.INVISIBLE
                binding.tvIntroOnboardingWarn.visibility = View.VISIBLE
            } else {
                binding.tvIntroOnboardingApprove.visibility = View.VISIBLE
                binding.tvIntroOnboardingWarn.visibility = View.INVISIBLE
            }
        }

        nextBtnActive()
    }

}