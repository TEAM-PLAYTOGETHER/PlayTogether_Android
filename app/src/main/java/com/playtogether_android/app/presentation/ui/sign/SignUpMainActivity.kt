package com.playtogether_android.app.presentation.ui.sign

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySignUpMainBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class SignUpMainActivity : BaseActivity<ActivitySignUpMainBinding>(R.layout.activity_sign_up_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initIdTextField()
        initPwTextField()
        initPwCheckTextField()
        pwCheckTextWatcher()
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

    //비밀번호 확인 textwatcher
    private fun pwCheckTextWatcher() = with(binding) {
        if(etSignupmainPwCheck.text.isEmpty()){
            tvSignupmainPwExpressionCheck.visibility = View.VISIBLE
            tvSignupmainPwExpressionCheckTrue.visibility = View.INVISIBLE
            tvSignupmainPwExpressionCheckFalse.visibility = View.INVISIBLE
        } else {
            etSignupmainPwCheck.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {

                    //pw, pw확인 같은지 check
                    if (etSignupmainPw.text.toString() == etSignupmainPwCheck.text.toString()) {
                        tvSignupmainPwExpressionCheck.visibility = View.INVISIBLE
                        tvSignupmainPwExpressionCheckTrue.visibility = View.VISIBLE
                        tvSignupmainPwExpressionCheckFalse.visibility = View.INVISIBLE
                    } else {
                        tvSignupmainPwExpressionCheck.visibility = View.INVISIBLE
                        tvSignupmainPwExpressionCheckTrue.visibility = View.INVISIBLE
                        tvSignupmainPwExpressionCheckFalse.visibility = View.VISIBLE
                    }

                }
            })
        }
    }
}