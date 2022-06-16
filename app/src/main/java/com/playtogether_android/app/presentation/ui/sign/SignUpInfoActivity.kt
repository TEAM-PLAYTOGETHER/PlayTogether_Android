package com.playtogether_android.app.presentation.ui.sign

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySignUpInfoBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import com.playtogether_android.domain.model.sign.SignUpItem
import dagger.hilt.android.AndroidEntryPoint
import java.lang.String
import java.util.*

@AndroidEntryPoint
class SignUpInfoActivity : BaseActivity<ActivitySignUpInfoBinding>(R.layout.activity_sign_up_info) {

    private val signViewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDatePickerDialog()
        initNameField()
        initGender()
        movePage()
        backBtn()
    }

    //이름
    private fun initNameField() = with(binding) {
        etSignupinfoName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                etSignupinfoName.isSelected = true
                activeNextBtn()
            }
        })
    }

    //성별
    private fun initGender() = with(binding) {
        tvSignupinfoMan.setOnClickListener {
            tvSignupinfoMan.isSelected = true
            if (tvSignupinfoWoman.isSelected) {
                tvSignupinfoWoman.isSelected = false
                activeNextBtn()
            }
        }

        tvSignupinfoWoman.setOnClickListener {
            tvSignupinfoWoman.isSelected = true
            if (tvSignupinfoMan.isSelected) {
                tvSignupinfoMan.isSelected = false
            }
            activeNextBtn()
        }
    }

    //데이트 피커
    private fun initDatePickerDialog() {
        binding.clPickerIcon.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(
                this,
                R.style.MySpinnerDatePickerStyle,
                { _, year, month, day ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_YEAR, day)

                    val month = month + 1
                    val dateString = String.format("%d.%02d.%02d", year, month, day)

                    binding.etSignupinfoBirth.setText(dateString)
                    binding.etSignupinfoBirth.setTextColor(Color.parseColor("#191919"))
                    binding.etSignupinfoBirth.isSelected = true

                    binding.ivSignupinfoDatepickerGray.visibility = View.INVISIBLE
                    binding.ivSignupinfoDatepickerBlack.visibility = View.VISIBLE
                    activeNextBtn()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),

                )
            val textColor = ContextCompat.getColor(
                this, R.color.gray_black
            )
            datePickerDialog.show()
            datePickerDialog.getButton(
                DatePickerDialog.BUTTON_POSITIVE
            ).setTextColor(textColor)
            datePickerDialog.getButton(
                DatePickerDialog.BUTTON_NEGATIVE
            ).setTextColor(textColor)
        }

    }

    //이전 버튼
    private fun backBtn() {
        binding.ivSignupinfoBack.setOnClickListener {
            val intent = Intent(this, SignUpMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //다음 버튼 활성화
    private fun activeNextBtn() = with(binding) {
        tvSignupinfoFinish.isSelected =
            etSignupinfoName.text.toString() != "" && etSignupinfoBirth.text.toString() != "" && (tvSignupinfoWoman.isSelected == true || tvSignupinfoMan.isSelected == true)
    }

    //다음으로 이동
    private fun movePage() {
        binding.tvSignupinfoFinish.setOnClickListener {
            signViewModel.requestSignUp.userName = binding.etSignupinfoName.text.toString()

            //gender
            var gender = ""
            if (binding.tvSignupinfoMan.isSelected) {
                gender = "남성"
                Log.d("gender", gender)
            } else {
                gender = "여성"
                Log.d("gender", gender)
            }


            signViewModel.requestSignUp.birth =
                binding.etSignupinfoBirth.text.toString().replace(".", "-")
            signViewModel.requestSignUp.gender = gender

            if (binding.tvSignupinfoFinish.isSelected) {

                signViewModel.postSignUp(
                    SignUpItem(
                        intent.getStringExtra("userLoginId").toString(),
                        intent.getStringExtra("password").toString(),
                        signViewModel.requestSignUp.userName,
                        signViewModel.requestSignUp.gender,
                        signViewModel.requestSignUp.birth,
                        "ESFJ"
                    )
                )


                val intent = Intent(this, SignUpFinishActivity::class.java)
                intent.putExtra("userName", binding.etSignupinfoName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}