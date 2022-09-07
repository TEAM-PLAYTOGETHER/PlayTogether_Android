package com.playtogether_android.app.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.DialogCheckBinding
import com.playtogether_android.app.databinding.DialogYesNoBinding
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity

class CustomDialogSon(context: Context) : Dialog(context) {
    private lateinit var onClickedListener: CustomDialog.ButtonClickListener

    fun setOnClickedListener(listener: CustomDialog.ButtonClickListener) {
        onClickedListener = listener
    }

    fun showChoice(title: String) {
        val binding = DialogYesNoBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.tvDialogTitle.text = title
//        findViewById<TextView>(R.id.tv_title).text = title
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        binding.tvDialogNo.setOnClickListener {
            dismiss()
        }
        binding.tvDialogYes.setOnClickListener {
            dismiss()
        }
//
//        findViewById<TextView>(R.id.tv_no).setOnClickListener {
//            dismiss()
//        }
//        findViewById<TextView>(R.id.tv_yes).setOnClickListener {
//            onClickedListener.onClicked(1)
//            dismiss()
//        }
    }

    fun showConfirmDialog(title: String) {
        val binding = DialogCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)
        show()
        binding.tvDialogTitle.text = title
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        binding.tvDialogCheck.setOnClickListener {
            onClickedListener.onClicked(1)
            dismiss()
            (context as ThunderDetailActivity).finish()
        }
//        findViewById<TextView>(R.id.tv_dialog_check).setOnClickListener {
//            onClickedListener.onClicked(1)
//            dismiss()
//            (context as ThunderDetailActivity).finish()
//        }
    }
}