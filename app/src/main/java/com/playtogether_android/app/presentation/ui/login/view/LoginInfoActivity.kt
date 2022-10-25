package com.playtogether_android.app.presentation.ui.login.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityLoginInfoBinding
import com.playtogether_android.app.databinding.DialogOnlyYearBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.SelectOnboardingActivity
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.domain.model.sign.UserInfo
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

@AndroidEntryPoint
class LoginInfoActivity : BaseActivity<ActivityLoginInfoBinding>(R.layout.activity_login_info) {
    private val signViewModel: SignViewModel by viewModels()
    private var birth: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnLogininfoMan.isSelected = true
        initView()
    }

    private fun initView() {
        val list = listOf(binding.btnLogininfoMan, binding.btnLogininfoWoman)
        setGenderClickListener(list)
        binding.clLogininfoDateContainer.setOnClickListener {
            initOnlyYearDatePickerDialog()
        }
        btnAccessButtonClickListener(list)
        backButtonListener()
    }

    private fun btnAccessButtonClickListener(list: List<AppCompatButton>) {
        binding.btnLogininfoAccess.setOnClickListener {
            val gender = list.filter {
                it.isSelected
            }
            val data = UserInfo(
                gender[0].text.toString(), birth
            )

            signViewModel.signup(data)
            signViewModel.isLogin.observe(this) {
                if (it) {
                    val intent = Intent(this, SelectOnboardingActivity::class.java)
                    intent.putExtra("case", 1)
                    startActivity(intent)
                } else {
                    shortToast("회원가입 실패!")
                }
            }
        }
    }

    private fun setGenderClickListener(list: List<Button>) {
        list[0].setOnClickListener {
            it.isSelected = true
            list[1].isSelected = false
        }
        list[1].setOnClickListener {
            it.isSelected = true
            list[0].isSelected = false
        }

    }

    private fun backButtonListener() {
        binding.ivLogintermBack.setOnClickListener {
            finish()
        }
    }

    private fun initOnlyYearDatePickerDialog() {
        val dialog = Dialog(this)
        val dialogBinding = DialogOnlyYearBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        val params = dialog.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        val time = Date(System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("yyyy", Locale("ko", "KR"))

        dialogBinding.tvDialogAccess.setOnClickListener {
            with(binding) {
                tvLogininfoDateText.apply {
                    this.text = dialogBinding.npDialogPicker.value.toString()
                    this.setTextAppearance(R.style.logininfo_year_text)
                }
                clLogininfoDateContainer.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
                birth = dialogBinding.npDialogPicker.value
                tvLogininfoDateText.text = birth.toString()
                ivLoginDate.setImageResource(R.drawable.ic_icn_calendar)
            }
            btnAccessButton()
            dialog.dismiss()
        }
        dialogBinding.tvDialogCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.npDialogPicker.apply {
            wrapSelectorWheel = false
            minValue = 1900
            maxValue = dateFormat.format(time).toString().toInt() - 19
            value = 2000
        }
        dialog.show()
    }

    private fun btnAccessButton() {
        binding.btnLogininfoAccess.apply {
            this.isClickable = true
            this.isSelected = true
        }
    }
}