package com.playtogether_android.app.presentation.ui.login.view

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.NumberPicker
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityLoginInfoBinding
import com.playtogether_android.app.databinding.DialogOnlyYearBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LoginInfoActivity : BaseActivity<ActivityLoginInfoBinding>(R.layout.activity_login_info) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnLogininfoMan.isSelected = true
        initView()
    }

    private fun initView() {
        binding.btnLogininfoMan.apply {
            setOnClickListener {
                this.isSelected = !this.isSelected
            }
        }
        binding.clLogininfoDateContainer.setOnClickListener {
            initOnlyYearDatePickerDialog()
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
}