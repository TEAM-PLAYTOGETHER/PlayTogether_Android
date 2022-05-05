package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityJoinOnBoardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class JoinOnBoardingActivity :
    BaseActivity<ActivityJoinOnBoardingBinding>(R.layout.activity_join_on_boarding) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBackBtn()
        initEditText()
        editTextWatcher()
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
    }


    //6자리 입력 해야 입장버튼 활성화
    private fun editTextWatcher() = with(binding) {
        etJoinOnboarding.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val input = binding.etJoinOnboarding.getText().toString()
                if (input.length == 5) {
                    binding.tvJoinOnboardingNext.visibility = View.VISIBLE
                    binding.tvJoinOnboardingEnter.visibility = View.INVISIBLE
                    Log.d("test", " " + input.length)
                } else {
                    binding.tvJoinOnboardingNext.visibility = View.INVISIBLE
                    binding.tvJoinOnboardingEnter.visibility = View.VISIBLE


                }

            }

        })
    }
}

