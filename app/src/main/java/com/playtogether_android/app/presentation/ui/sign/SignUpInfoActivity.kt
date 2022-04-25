package com.playtogether_android.app.presentation.ui.sign

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
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
    }


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

                    val month = month +1
                    val dateString = String.format("%d.%02d.%02d", year, month, day)

                    binding.etSignupinfoBirth.setText(dateString)
                    binding.etSignupinfoBirth.setTextColor(Color.parseColor("#191919"))
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
                DatePickerDialog.BUTTON_POSITIVE).setTextColor(textColor)
            datePickerDialog.getButton(
                DatePickerDialog.BUTTON_NEGATIVE).setTextColor(textColor)
        }

    }
}