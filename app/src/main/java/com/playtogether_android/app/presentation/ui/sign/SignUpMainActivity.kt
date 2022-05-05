package com.playtogether_android.app.presentation.ui.sign

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySignUpMainBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.regex.Pattern

class SignUpMainActivity : BaseActivity<ActivitySignUpMainBinding>(R.layout.activity_sign_up_main) {

    private val signViewModel: SignViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initIdTextField()
        initPwTextField()
        initPwCheckTextField()
        pwCheckTextWatcher()
        pwTextWatcher()
        actvieDuplicationBtn()
        idTextWatcher()
    }


    //id editText 클릭 리스너
    private fun initIdTextField() = with(binding) {
        etSignupmainId.setOnClickListener {
            etSignupmainId.isFocused
        }
    }


    //pw editText 클릭 리스너
    private fun initPwTextField() = with(binding) {
        etSignupmainPw.setOnClickListener {
            etSignupmainPw.isFocused
        }
    }

    //pw check editText 클릭 리스너
    private fun initPwCheckTextField() = with(binding) {
        etSignupmainPwCheck.setOnClickListener {
            etSignupmainPwCheck.isFocused
        }
    }

    //아이디 정규식
    private fun isVaildRegistrationId() = with(binding) {
        if(!Pattern.matches("^[a-z|0-9|]{8,15}\$", etSignupmainId.text.toString())){
            tvSignupmanIdDuplication.isSelected = false
            tvSignupmainIdExpressionWarn.visibility = View.VISIBLE
            tvSignupmainIdExpression.visibility = View.INVISIBLE
            Timber.d("정규식 맞지 않음")
        } else {
            tvSignupmanIdDuplication.isSelected = true
            tvSignupmainIdExpressionWarn.visibility = View.INVISIBLE
            tvSignupmainIdExpression.visibility = View.INVISIBLE
            tvSignupmanIdDuplication.isSelected = true
            Timber.d("정규식 맞지 않음")
        }
    }

    //중복 확인 버튼 활성화 클릭 리스너
    private fun actvieDuplicationBtn() = with(binding) {
        tvSignupmainNext.setOnClickListener {
            if (tvSignupmanIdDuplication.isSelected == true or tvSignupmainNext.isSelected) {
                startActivity(Intent(this@SignUpMainActivity, SignInActivity::class.java))
                finish()
            }
        }
    }


    //비밀번호 정규식
    private fun isValidRegistrationPw() = with(binding) {
        if (!Pattern.matches("^[a-z|A-Z|0-9|(!,@,#,$,&,*,(,))|]{8,15}", etSignupmainPw.text.toString())) {
            tvSignupmainPwExpression.setTextColor(Color.parseColor("#FF0000"))
            tvSignupmainPwExpression.visibility = View.VISIBLE
            ivPwCheck.visibility = View.INVISIBLE
            tvSignupmainPwExpressionOk.visibility = View.INVISIBLE
        } else {
            tvSignupmainPwExpression.visibility = View.INVISIBLE
            tvSignupmainPwExpressionOk.visibility = View.VISIBLE
            ivPwCheck.visibility = View.VISIBLE
            //textSignupBasicinfoPwTitle.setTextColor(Color.parseColor("#94959E"))
        }
    }


    //아이디 textwatcher
    private fun idTextWatcher()= with(binding) {
        etSignupmainId.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (signViewModel.id.value != etSignupmainId.text.toString()) {

                }
                isVaildRegistrationId()
            }

        })
    }

    //비밀번호 textwatcher
    private fun pwTextWatcher() = with(binding) {
        etSignupmainPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

                if (etSignupmainPw.text.toString() == "") {
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
        etSignupmainPwCheck.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if (etSignupmainPwCheck.text.toString() == "") {
                    tvSignupmainPwExpressionCheck.visibility = View.VISIBLE
                    tvSignupmainPwExpressionCheckTrue.visibility = View.INVISIBLE
                    tvSignupmainPwExpressionCheckFalse.visibility = View.INVISIBLE
                } else {

                    isPwSame()

                }
            }
        })
    }

    //비밀번호, 비밀번호 확인 일치 여부 체크 함수
    private fun isPwSame() = with(binding) {
        if (etSignupmainPw.text.toString() == etSignupmainPwCheck.text.toString()) {
            tvSignupmainPwExpressionCheck.visibility = View.INVISIBLE
            tvSignupmainPwExpressionCheckTrue.visibility = View.VISIBLE
            tvSignupmainPwExpressionCheckFalse.visibility = View.INVISIBLE
            ivPwCheckCheck.visibility= View.VISIBLE
        } else {
            if(etSignupmainPwCheck.text.toString()=="") {
                tvSignupmainPwExpressionCheck.visibility = View.VISIBLE
                tvSignupmainPwExpressionCheckTrue.visibility = View.INVISIBLE
                tvSignupmainPwExpressionCheckFalse.visibility = View.INVISIBLE
            } else {
                tvSignupmainPwExpressionCheck.visibility = View.INVISIBLE
                tvSignupmainPwExpressionCheckTrue.visibility = View.INVISIBLE
                tvSignupmainPwExpressionCheckFalse.visibility = View.VISIBLE
                ivPwCheckCheck.visibility = View.INVISIBLE
            }
        }
    }
}