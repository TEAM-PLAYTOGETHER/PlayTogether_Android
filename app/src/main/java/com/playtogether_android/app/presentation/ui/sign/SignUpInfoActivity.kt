package com.playtogether_android.app.presentation.ui.sign

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySignUpInfoBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import java.lang.String
import java.util.*

class SignUpInfoActivity : BaseActivity<ActivitySignUpInfoBinding>(R.layout.activity_sign_up_info) {
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
            if(tvSignupinfoWoman.isSelected) {
                tvSignupinfoWoman.isSelected = false
                activeNextBtn()
            }
        }

        tvSignupinfoWoman.setOnClickListener {
            tvSignupinfoWoman.isSelected = true
            if(tvSignupinfoMan.isSelected) {
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
            startActivity(Intent(this, SignUpMainActivity::class.java))
            finish()
        }
    }

    //다음 버튼 활성화
    private fun activeNextBtn() = with(binding){
        tvSignupinfoFinish.isSelected = etSignupinfoName.text.toString() != "" && etSignupinfoBirth.text.toString() != "" && (tvSignupinfoWoman.isSelected == true || tvSignupinfoMan.isSelected == true)
    }

    //다음으로 이동
    private fun movePage() {
        binding.tvSignupinfoFinish.setOnClickListener {
            if(binding.tvSignupinfoFinish.isSelected) {
                startActivity(Intent(this, SignUpFinishActivity::class.java))
                finish()
            }
        }
    }
}