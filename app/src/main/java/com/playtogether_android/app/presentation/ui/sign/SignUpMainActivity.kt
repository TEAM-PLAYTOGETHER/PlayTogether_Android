package com.playtogether_android.app.presentation.ui.sign

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySignUpMainBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import timber.log.Timber
import java.util.regex.Pattern

class SignUpMainActivity : BaseActivity<ActivitySignUpMainBinding>(R.layout.activity_sign_up_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initIdTextField()
        initPwTextField()
        initPwCheckTextField()
        pwCheckTextWatcher()
        pwTextWatcher()
    }

    //id editText 클릭 리스너
    private fun initIdTextField() {
        binding.etSignupmainId.setOnClickListener {
            binding.etSignupmainId.isFocused
        }
    }


    //pw editText 클릭 리스너
    private fun initPwTextField() {
        binding.etSignupmainPw.setOnClickListener {
            binding.etSignupmainPw.isFocused
        }
    }

    //pw check editText 클릭 리스너
    private fun initPwCheckTextField() {
        binding.etSignupmainPwCheck.setOnClickListener {
            binding.etSignupmainPwCheck.isFocused
        }
    }

    //비밀번호 정규식
    fun isValidRegistrationPw() = with(binding) {
        if (!Pattern.matches(
                "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}\$",
                etSignupmainPw.text.toString()
            )
        ) {
            tvSignupmainPwExpression.setTextColor(Color.parseColor("#FF0000"))
        } else {
            tvSignupmainPwExpression.visibility = View.INVISIBLE
            tvSignupmainPwExpressionOk.visibility = View.VISIBLE
            Log.d("찍히나", "test")
            //textSignupBasicinfoPwTitle.setTextColor(Color.parseColor("#94959E"))
        }
    }

    //비밀번호 textwatcher
    private fun pwTextWatcher() = with(binding) {
        etSignupmainPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

                if(etSignupmainPw.text.toString()== ""){
                    tvSignupmainPwExpression.visibility = View.VISIBLE
                    tvSignupmainPwExpressionOk.visibility = View.INVISIBLE
                } else {
                    isPwSame()
                }
                isValidRegistrationPw()
            }

        })
    }

    //비밀번호 확인 textwatcher
    private fun pwCheckTextWatcher() = with(binding) {
        if (etSignupmainPwCheck.text.isEmpty()) {
            tvSignupmainPwExpressionCheck.visibility = View.VISIBLE
            tvSignupmainPwExpressionCheckTrue.visibility = View.INVISIBLE
            tvSignupmainPwExpressionCheckFalse.visibility = View.INVISIBLE
        } else {
            etSignupmainPwCheck.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {

                    isPwSame()

                }
            })
        }
    }

    //비밀번호, 비밀번호 확인 일치 여부 체크 함수
    private fun isPwSame() = with(binding) {
        if (etSignupmainPw.text.toString() == etSignupmainPwCheck.text.toString()) {
            tvSignupmainPwExpressionCheck.visibility = View.INVISIBLE
            tvSignupmainPwExpressionCheckTrue.visibility = View.VISIBLE
            tvSignupmainPwExpressionCheckFalse.visibility = View.INVISIBLE
            Timber.d("비밀번호 일치")
        } else {
            tvSignupmainPwExpressionCheck.visibility = View.INVISIBLE
            tvSignupmainPwExpressionCheckTrue.visibility = View.INVISIBLE
            tvSignupmainPwExpressionCheckFalse.visibility = View.VISIBLE
            Timber.d("비밀번호 일치하지 않음")
        }
    }
}