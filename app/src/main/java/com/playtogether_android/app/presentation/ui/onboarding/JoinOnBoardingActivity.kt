package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter.AllCaps
import android.text.InputFilter.LengthFilter
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityJoinOnBoardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.main.MainActivity
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.app.util.CustomDialog
import com.playtogether_android.domain.model.onboarding.RegisterCrewItem
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinOnBoardingActivity :
    BaseActivity<ActivityJoinOnBoardingBinding>(R.layout.activity_join_on_boarding) {

    private val onBoardingViewModel: OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBackBtn()
        initEditText()
        editTextWatcher()
        initRegisterCrew()
    }

    //뒤로가기 버튼 클릭 리스너
    private fun initBackBtn() {
        binding.ivJoinOnboardingBack.setOnClickListener {
            startActivity(Intent(this, SecondOnboardingActivity::class.java))
            finish()
        }
    }


    //edittext 입력중일 때 텍스트 백그라운드 selected
    private fun initEditText() {
        binding.etJoinOnboarding.setOnClickListener {
            binding.etJoinOnboarding.isSelected = true
        }
        binding.etJoinOnboarding.filters = arrayOf(AllCaps(), LengthFilter(6))
    }


    //6자리 입력 해야 입장버튼 활성화
    private fun editTextWatcher() = with(binding) {
        etJoinOnboarding.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val input = binding.etJoinOnboarding.text.toString()
                if (input.length == 5) {
                    binding.tvJoinOnboardingNext.visibility = View.VISIBLE
                    binding.tvJoinOnboardingEnter.visibility = View.INVISIBLE
                } else {
                    binding.tvJoinOnboardingNext.visibility = View.INVISIBLE
                    binding.tvJoinOnboardingEnter.visibility = View.VISIBLE


                }

            }

        })
    }

    //동아리 가입
    private fun observeRegisterCrew() {
        onBoardingViewModel.registerCrew.observe(this) {
            if (it.success) {
                Log.d("성공", "동아리가입")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.d("실패", "동아리가입")
//                val title = "존재하지 않는 코드입니다"
//                val dialog = CustomDialog(this, title)
//                dialog.showOneChoiceDialog(R.layout.dialog_one_question)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun initRegisterCrew() {
        binding.tvJoinOnboardingEnter.setOnClickListener {
            onBoardingViewModel.crewCode.crewCode = binding.etJoinOnboarding.text.toString()
            onBoardingViewModel.postRegisterCrew(
                RegisterCrewItem(
                    onBoardingViewModel.crewCode.crewCode
                )
            )
            observeRegisterCrew()
        }
    }
}

