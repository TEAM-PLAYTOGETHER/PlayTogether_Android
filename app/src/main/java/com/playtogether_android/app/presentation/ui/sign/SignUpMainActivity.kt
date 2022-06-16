package com.playtogether_android.app.presentation.ui.sign

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySignUpMainBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import com.playtogether_android.app.util.CustomDialog
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.regex.Pattern

@AndroidEntryPoint
class SignUpMainActivity : BaseActivity<ActivitySignUpMainBinding>(R.layout.activity_sign_up_main) {

    private val signViewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nullCheck()
        pwCheckTextWatcher()
        pwTextWatcher()
        actvieNextBtn()
        idTextWatcher()
        backBtn()
        duplicationClickEvent()
    }


    //빈칸 체크
    private fun nullCheck() {
        binding.etSignupmainId.setOnClickListener {
            initTextFieldCheck()
        }

        binding.etSignupmainPw.setOnClickListener {
            initTextFieldCheck()
        }

        binding.etSignupmainPwCheck.setOnClickListener {
            initTextFieldCheck()
        }
    }


    //TextField 빈칸 체크
    private fun initTextFieldCheck() {
        if (binding.etSignupmainId.text.toString() != "") {
            binding.etSignupmainId.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
        } else {
            binding.etSignupmainId.setBackgroundResource(R.drawable.selector_rectangle_border_gray03_to_black02)
        }

        if (binding.etSignupmainPw.text.toString() != "") {
            binding.etSignupmainPw.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
        } else {
            binding.etSignupmainPw.setBackgroundResource(R.drawable.selector_rectangle_border_gray03_to_black02)
        }

        if (binding.etSignupmainPwCheck.text.toString() != "") {
            binding.etSignupmainPwCheck.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
        } else {
            binding.etSignupmainPwCheck.setBackgroundResource(R.drawable.selector_rectangle_border_gray03_to_black02)
        }

    }



    //아이디 정규식
    private fun isVaildRegistrationId() = with(binding) {
        if (!Pattern.matches("^[a-z|0-9|]{5,20}\$", etSignupmainId.text.toString())) {
            tvSignupmainIdDuplication.isSelected = false
            tvSignupmainIdExpressionWarn.visibility = View.VISIBLE
            tvSignupmainIdExpression.visibility = View.INVISIBLE
            Timber.d("정규식 맞지 않음")
        } else {
            tvSignupmainIdDuplication.isSelected = true
            tvSignupmainIdExpressionWarn.visibility = View.INVISIBLE
            tvSignupmainIdExpression.visibility = View.INVISIBLE
            tvSignupmainIdDuplication.isSelected = true
            Timber.d("정규식 맞지 않음")
        }
    }

    //다음 페이지로 이동
    private fun actvieNextBtn() = with(binding) {
        signViewModel.requestSignUp.userLoginId = etSignupmainId.text.toString()
        signViewModel.requestSignUp.password = etSignupmainPwCheck.text.toString()

        tvSignupmainNext.setOnClickListener {
            if (tvSignupmainNext.isSelected) {
                val intent = Intent(this@SignUpMainActivity, SignUpInfoActivity::class.java)
                intent.putExtra("userLoginId", etSignupmainId.text.toString())
                intent.putExtra("password", etSignupmainPwCheck.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }

    //다음 버튼 활성화
    private fun allChecked() = with(binding) {
        if (ivIdCheck.visibility == View.VISIBLE && ivPwCheck.visibility == View.VISIBLE && ivPwCheckCheck.visibility == View.VISIBLE) {
            tvSignupmainNext.isSelected = true
        }
    }


    //비밀번호 정규식
    private fun isValidRegistrationPw() = with(binding) {
        if (!Pattern.matches(
                "^[a-z|A-Z|0-9|(!,@,#,$,&,*,(,))|]{8,15}",
                etSignupmainPw.text.toString()
            )
        ) {
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
    private fun idTextWatcher() = with(binding) {
        etSignupmainId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

                initTextFieldCheck()

                val id = signViewModel.id.value

                if (id != etSignupmainId.text.toString()) {
                    binding.ivIdCheck.visibility = View.INVISIBLE
                    binding.tvSignmainIdDuplication.visibility = View.INVISIBLE
                    binding.tvSignupmainIdDuplication.visibility = View.VISIBLE
                }
                isVaildRegistrationId()
                allChecked()
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

                initTextFieldCheck()

                if (etSignupmainPw.text.toString() == "") {
                    tvSignupmainPwExpression.visibility = View.VISIBLE
                    tvSignupmainPwExpressionOk.visibility = View.INVISIBLE
                } else {
                    isPwSame()
                }
                isValidRegistrationPw()
                allChecked()
            }

        })
    }

    //비밀번호 확인 textwatcher
    private fun pwCheckTextWatcher() = with(binding) {
        etSignupmainPwCheck.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {

                initTextFieldCheck()

                if (etSignupmainPwCheck.text.toString() == "") {
                    tvSignupmainPwExpressionCheck.visibility = View.VISIBLE
                    tvSignupmainPwExpressionCheckTrue.visibility = View.INVISIBLE
                    tvSignupmainPwExpressionCheckFalse.visibility = View.INVISIBLE
                } else {

                    isPwSame()

                }
                allChecked()
            }
        })
    }

    //비밀번호, 비밀번호 확인 일치 여부 체크 함수
    private fun isPwSame() = with(binding) {
        if (etSignupmainPw.text.toString() == etSignupmainPwCheck.text.toString()) {
            tvSignupmainPwExpressionCheck.visibility = View.INVISIBLE
            tvSignupmainPwExpressionCheckTrue.visibility = View.VISIBLE
            tvSignupmainPwExpressionCheckFalse.visibility = View.INVISIBLE
            ivPwCheckCheck.visibility = View.VISIBLE
        } else {
            if (etSignupmainPwCheck.text.toString() == "") {
                tvSignupmainPwExpressionCheck.visibility = View.VISIBLE
                tvSignupmainPwExpressionCheckTrue.visibility = View.INVISIBLE
                tvSignupmainPwExpressionCheckFalse.visibility = View.INVISIBLE
            } else {
                tvSignupmainPwExpressionCheck.visibility = View.INVISIBLE
                tvSignupmainPwExpressionCheckTrue.visibility = View.INVISIBLE
                tvSignupmainPwExpressionCheckFalse.visibility = View.VISIBLE
                ivPwCheckCheck.visibility = View.INVISIBLE
            }
            allChecked()
        }

    }

    //이전 버튼
    private fun backBtn() {
        binding.ivSignupmainBack.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }

    //id 중복체크
    private fun idDuplicationCheck() {
        val id = binding.etSignupmainId.text.toString()
        signViewModel.postIdDuplication(
            IdDuplicationCheckItem(id)
        )

        signViewModel.idDuplicationCheck.observe(this) {
            if (it.isUser == true) {
                Log.d("중복확인", "중복되는 아이디 있음")
                showApplyDialog()
            } else {
                Log.d("중복확인", "중복되는 아이디 없음")
                binding.ivIdCheck.visibility = View.VISIBLE
                binding.tvSignmainIdDuplication.visibility = View.VISIBLE
                binding.tvSignupmainIdDuplication.visibility = View.INVISIBLE
            }
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


    //customDialog
    private fun showApplyDialog() {
        val title = "중복된 아이디입니다"
        val dialog = CustomDialog(this, title)
        dialog.showOneChoiceDialog(R.layout.dialog_one_question)
    }


}