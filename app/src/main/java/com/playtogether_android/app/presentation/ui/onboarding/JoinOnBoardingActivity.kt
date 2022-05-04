package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityJoinOnBoardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import timber.log.Timber

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


    //edittext selector
    private fun initEditText() {
        binding.etJoinOnboarding.setOnClickListener {
            binding.etJoinOnboarding.isSelected = true
        }
    }

    //빈칸인지 체크
    private fun editTextBlankCheck() {
        if (binding.etJoinOnboarding.text.toString() != "") {
            binding.etJoinOnboarding.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)

            binding.tvJoinOnboardingNext.visibility = View.INVISIBLE
            binding.tvJoinOnboardingEnter.visibility = View.VISIBLE

            Timber.d("빈칸 아님")
        } else {
            binding.tvJoinOnboardingNext.visibility = View.VISIBLE
            binding.tvJoinOnboardingEnter.visibility = View.INVISIBLE
            Timber.d("빈칸")
        }
    }

    private fun editTextWatcher() = with(binding) {
        etJoinOnboarding.addTextChangedListener { object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                editTextBlankCheck()
                Timber.d("바뀜")
            }

        } }
    }

}