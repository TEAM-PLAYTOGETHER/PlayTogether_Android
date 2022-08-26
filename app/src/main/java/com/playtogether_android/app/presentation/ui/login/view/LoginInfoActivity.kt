package com.playtogether_android.app.presentation.ui.login.view

import android.app.Dialog
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityLoginInfoBinding
import com.playtogether_android.app.databinding.DialogOnlyYearBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginInfoActivity : BaseActivity<ActivityLoginInfoBinding>(R.layout.activity_login_info) {
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
        backButtonListener()
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

        dialogBinding.tvDialogAccess.setOnClickListener {
            with(binding) {
                tvLogininfoDateText.apply {
                    this.text = dialogBinding.npDialogPicker.value.toString()
                    this.setTextAppearance(R.style.logininfo_year_text)
                }
                clLogininfoDateContainer.setBackgroundResource(R.drawable.rectangle_border_gray01_radius_10)
                tvLogininfoDateText.text = dialogBinding.npDialogPicker.value.toString()
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
            maxValue = 2022
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