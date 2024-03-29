package com.playtogether_android.app.presentation.ui.userInfo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.chip.Chip
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityEditProfileBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.SearchSubwayActivity
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.onboarding.AddProfileItem
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.regex.Pattern

@AndroidEntryPoint
class EditProfileActivity :
    BaseActivity<ActivityEditProfileBinding>(R.layout.activity_edit_profile) {

    private val onBoardingViewModel: OnBoardingViewModel by viewModels()
    private val chipList = java.util.ArrayList<String>()
    private var firstSubway: String? = null
    private var secondSubway: String? = null
    private val isEdit = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backBtnListener()
        nameTextWatcher()
        introTextWatcher()
        nextBtnClickListener()
        subwayBtnListener()
        setChipBtn()
        nullCheck()
        initSetting()
        nextBtnActive()
    }

    override fun onResume() {
        super.onResume()
        nicknameDuplicationCheck()
        initSetting()
    }

    private fun nextBtnClickListener() {
        binding.tvIntroOnboardingNext.setOnClickListener {
            if (binding.tvIntroOnboardingNext.isSelected) {
                nextBtnNetwork()
            }
        }
    }


    private fun nextBtnNetwork() {
        val nickName = binding.etIntroOnboardingName.text.toString()
        val description = binding.etIntroOnboardingIntro.text.toString()

        for (i: Int in 1..binding.chipMypage.childCount) {
            val chip: Chip = binding.chipMypage.getChildAt(i - 1) as Chip
            chipList.add(chip.text.toString())
        }

        val list = chipList
       //val list = intent.getStringArrayListExtra("ChipList")
        if (list?.size != null) {
            for (i in 0 until list.size) {
                val chip = Chip(binding.chipMypage.context).apply {
                    text = list[i]
                    Timber.e("TEST ${list[i]}")
                    firstSubway = list[0]
                    if (list.size < 2) {
                        secondSubway = null
                    } else {
                        secondSubway = list[1]
                    }
                }
            }
        }

        onBoardingViewModel.putAddProfile(
            AddProfileItem(
                description,
                firstSubway,
                nickName,
                secondSubway
            ), PlayTogetherRepository.crewId
        )

        Timber.e("TEST0 : ${PlayTogetherRepository.crewId}")
        Timber.e("TEST1 : $description")
        Timber.e("Test2 : $firstSubway")
        Timber.e("Test3 : $nickName")
        Timber.e("Test4 : $secondSubway")


        finish()


    }

    private fun nextBtnActive() {
        if (binding.tvIntroOnboardingApprove.visibility == View.VISIBLE && binding.etIntroOnboardingIntro.text.toString() != "") {
            binding.tvIntroOnboardingNext.isSelected = true
        } else {
            binding.tvIntroOnboardingNext.isSelected = false
        }

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
        val nicknameCheck = intent.getBooleanExtra("nicknameCheck", false)

        if (nicknameCheck) {
            nicknameDuplicationCheck()
        }

        binding.tvIntroOnboardingCrewName.setText(crewName)
        binding.etIntroOnboardingName.setText(nickname)
        binding.etIntroOnboardingIntro.setText(description)

        if (description != null && nicknameCheck == true) {
            binding.tvIntroOnboardingNext.isSelected = true
            binding.tvIntroOnboardingNext.setOnClickListener {
                nextBtnNetwork()
            }
        } else {
            binding.tvIntroOnboardingNext.isSelected = false
        }
    }

    //뒤로가기 버튼 리스너
    private fun backBtnListener() {
        binding.ivIntroOnboardingBack.setOnClickListener {
            finish()
        }
    }

    //et 비었는지 체크
    private fun nullCheck() {
        binding.etIntroOnboardingName.setOnClickListener { initTextFieldCheck() }
        binding.etIntroOnboardingIntro.setOnClickListener { initTextFieldCheck() }
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
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val name = p0.toString()
                if (name.isEmpty() || binding.etIntroOnboardingName.text.toString() == "") {
                    binding.tvSignupmainIdDuplication.isSelected = false
                    binding.tvSignupmainIdDuplication.isClickable = false
                    binding.tvIntroOnboardingCondition.setTextColor(Color.parseColor("#C5C5C5"))
                } else if (!Pattern.matches("^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z|A-Z|0-9|0-9|_]{2,10}\$", name)) {
                    binding.tvSignupmainIdDuplication.isSelected = false
                    binding.tvSignupmainIdDuplication.isClickable = false
                    binding.tvIntroOnboardingCondition.setTextColor(Color.parseColor("#FF0000"))
                } else {
                    binding.tvSignupmainIdDuplication.isSelected = true
                    binding.tvSignupmainIdDuplication.isClickable = true
                    binding.tvIntroOnboardingCondition.setTextColor(Color.parseColor("#C5C5C5"))
                }
                nextBtnActive()
            }

            override fun afterTextChanged(p0: Editable?) {
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
                binding.tvIntroOnboardingNext.isSelected = false
                nextBtnActive()
            }
        })
        nextBtnActive()
        binding.tvIntroOnboardingNext.isSelected = false
    }

    //간단소개 textWatcher
    private fun introTextWatcher() = with(binding) {
        etIntroOnboardingIntro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nextBtnActive()
            }

            override fun afterTextChanged(p0: Editable?) {
                etIntroOnboardingIntro.isSelected = etIntroOnboardingIntro.text.toString() != ""
                initTextFieldCheck()
                nextBtnActive()
            }
        })
        nextBtnActive()
    }

    private fun subwayBtnListener() {
        binding.tvOpenOnboardingAdd.setOnClickListener {
            val crewName = intent.getStringExtra("crewName")
            val crewCode = intent.getStringExtra("crewCode")
            val crewIntroduce = intent.getStringExtra("crewIntro")
            val crewId = intent.getIntExtra("crewId", 1)

            if (binding.tvOpenOnboardingAdd.isSelected) {
                shortToast("최대 2개까지 추가할 수 있어요!")
            } else {
                for (i: Int in 1..binding.chipMypage.childCount) {
                    val chip: Chip = binding.chipMypage.getChildAt(i - 1) as Chip
                    chipList.add(chip.text.toString())
                }

                val name = binding.etIntroOnboardingName.text.toString()
                val description = binding.etIntroOnboardingIntro.text.toString()

                binding.tvIntroOnboardingCrewName.text = crewName

                val intent = Intent(this, SearchSubwayActivity::class.java).apply {
                    putExtra("nickname", name)
                    putExtra("crewName", crewName)
                    putExtra("crewCode", crewCode)
                    putExtra("crewId", crewId)
                    putExtra("crewIntro", crewIntroduce)
                    putExtra("isEdit", isEdit)

                    putExtra("ChipList", chipList)
                    putExtra("description", description)
                }

                if (binding.tvIntroOnboardingApprove.visibility == View.VISIBLE || binding.tvIntroOnboardingWarn.visibility == View.VISIBLE) {
                    intent.putExtra("nicknameCheck", true)
                } else {
                    intent.putExtra("nicknameCheck", false)
                }
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

    //닉네임 중복 확인
    private fun nicknameDuplicationCheck() {
        val nickname: String = binding.etIntroOnboardingName.text.toString()

        val crewId = intent.getIntExtra("crewId", 1)

        onBoardingViewModel.getNickNameDuplication(crewId, nickname)
        onBoardingViewModel.nicknameDuplicationCheck.observe(this) {
            if (!it.success) {
                binding.tvIntroOnboardingApprove.visibility = View.INVISIBLE
                binding.tvIntroOnboardingWarn.visibility = View.VISIBLE
                nextBtnActive()
            } else {
                binding.tvIntroOnboardingApprove.visibility = View.VISIBLE
                binding.tvIntroOnboardingWarn.visibility = View.INVISIBLE
                nextBtnActive()
            }
        }
    }
}