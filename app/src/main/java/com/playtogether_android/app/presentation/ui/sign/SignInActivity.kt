package com.playtogether_android.app.presentation.ui.sign

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySignInBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.main.MainActivity
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import com.playtogether_android.app.util.CustomDialog
import com.playtogether_android.domain.model.sign.SignInItem
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    private val signViewModel: SignViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSignUpBtn()
        idTextWatcher()
        pwTextWatcher()
        initIdTextField()
        initPwTextField()
        initSignIn()
        observeSignIn()

    }

    //회원가입 클릭 리스너
    private fun initSignUpBtn() {
        binding.textSignInSignup.setOnClickListener {
            startActivity(Intent(this, SignUpMainActivity::class.java))
            finish()
        }
    }

    //아이디 TextField 클릭
    private fun initIdTextField() {
        binding.etSigninId.setOnClickListener {
            if (binding.etSigninPw.text.toString() != "") {
                binding.etSigninPw.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
            }
        }
    }

    //비밀번호 TextField 클릭
    private fun initPwTextField() {
        binding.etSigninPw.setOnClickListener {
            if (binding.etSigninId.text.toString() != "") {
                binding.etSigninId.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
            }
        }
    }

    //다음버튼 활성화
    private fun nextBtnActive() {
        binding.tvSignInLogIn.isSelected =
            binding.etSigninId.text.toString() != "" && binding.etSigninId.text.toString() != ""
    }

    //로그인
    private fun initSignIn() {
        if (binding.tvSignInLogIn.isSelected) {
            binding.tvSignInLogIn.setOnClickListener {
                signViewModel.postSignIn(
                    SignInItem(
                        signViewModel.id.value.toString(),
                        signViewModel.pw.value.toString()
                    )
                )
                Log.d("로그인", "Test")

            }
        }

    }

    //로그인
    private fun observeSignIn() {
        signViewModel.signUp.observe(this) {
            if (signViewModel.signUp.value?.success == true) {
                Log.d("성공", "로그인")
                //val intent = Intent(this, MainActivity::class.java)
                //startActivity(intent)
                //finish()
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
            }

        })

    }

    //비밀번호 textwatcher
    private fun pwTextWatcher() = with(binding) {
        etSigninId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                etSigninPw.isSelected = etSigninPw.text.toString() != ""
                nextBtnActive()
            }

        })
    }

}