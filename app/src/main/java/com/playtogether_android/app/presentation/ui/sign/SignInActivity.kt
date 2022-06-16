package com.playtogether_android.app.presentation.ui.sign

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySignInBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.FirstOnBoardingActivity
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import com.playtogether_android.app.util.CustomDialog
import com.playtogether_android.app.util.PlayTogetherSharedPreference
import com.playtogether_android.domain.model.sign.SignInItem
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    private val signViewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSignUpBtn()
        idTextWatcher()
        pwTextWatcher()
        nullCheck()
    }

    //회원가입 클릭 리스너
    private fun initSignUpBtn() {
        binding.textSignInSignup.setOnClickListener {
            startActivity(Intent(this, SignUpMainActivity::class.java))
            finish()
        }
    }

    //빈칸 체크
    private fun nullCheck() {
        binding.etSigninPw.setOnClickListener {
            initTextFieldCheck()
        }

        binding.etSigninId.setOnClickListener {
            initTextFieldCheck()
        }
    }


    //TextField 빈칸 체크
    private fun initTextFieldCheck() {
        if (binding.etSigninPw.text.toString() != "") {
            binding.etSigninPw.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
        } else {
            binding.etSigninPw.setBackgroundResource(R.drawable.selector_rectangle_border_gray03_to_black02)
        }

        if (binding.etSigninId.text.toString() != "") {
            binding.etSigninId.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
        } else {
            binding.etSigninId.setBackgroundResource(R.drawable.selector_rectangle_border_gray03_to_black02)
        }

    }

    //로그인 버튼 활성화
    private fun nextBtnActive() {
        if (binding.etSigninId.text.toString() == "" || binding.etSigninPw.text.toString() == "") {
            binding.tvSignInLogIn.isSelected = false

        } else {
            binding.tvSignInLogIn.isSelected = true
            initSignIn()
        }
    }

    //로그인
    private fun initSignIn() {
        binding.tvSignInLogIn.setOnClickListener {
            signViewModel.requestSignIn.userLoginId = binding.etSigninId.text.toString()
            signViewModel.requestSignIn.password = binding.etSigninPw.text.toString()
            if (binding.tvSignInLogIn.isSelected) {
                signViewModel.postSignIn(
                    SignInItem(
                        signViewModel.requestSignIn.userLoginId,
                        signViewModel.requestSignIn.password
                    )
                )
                observeSignIn()
            }

        }

    }

    //로그인
    private fun observeSignIn() {
        signViewModel.signIn.observe(this) {
            if (it.success) {
                PlayTogetherSharedPreference.setJwtToken(
                    this,
                    it.jwtToken
                )
                val intent = Intent(this, FirstOnBoardingActivity::class.java)
                intent.putExtra("userName", signViewModel.signIn.value!!.userName)
                startActivity(intent)
                finish()
            } else {
                showApplyDialog()
            }
        }
    }

    //커스텀 다이얼로그
    private fun showApplyDialog() {
        val title = "아이디 혹은 비밀번호를\n확인해주세요"
        val dialog = CustomDialog(this, title)
        dialog.showOneChoiceDialog(R.layout.dialog_one_question)
        Log.d("Test", "CustomDialog")
    }


    //아이디 textwatcher
    private fun idTextWatcher() = with(binding) {
        etSigninId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                etSigninId.isSelected = etSigninId.text.toString() != ""
                nextBtnActive()
                initTextFieldCheck()
            }

        })


    }

    //비밀번호 textwatcher
    private fun pwTextWatcher() = with(binding) {
        etSigninPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                etSigninPw.isSelected = etSigninPw.text.toString() != ""
                nextBtnActive()
                initTextFieldCheck()
            }

        })
    }

}