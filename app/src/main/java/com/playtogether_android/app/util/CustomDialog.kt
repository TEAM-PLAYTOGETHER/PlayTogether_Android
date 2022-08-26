package com.playtogether_android.app.util

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.playtogether_android.app.R
import com.playtogether_android.app.presentation.ui.sign.SignInActivity
import com.playtogether_android.app.presentation.ui.thunder.ApplyThunderDetailActivity
import com.playtogether_android.app.presentation.ui.thunder.OpenThunderDetailActivity
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

class CustomDialog(private val context: Context, val title: String) {
    private val dialog = Dialog(context)
    private lateinit var onClickedListener: ButtonClickListener

    interface ButtonClickListener {
        fun onClicked(num: Int)
    }

    fun setOnClickedListener(listener: ButtonClickListener) {
        onClickedListener = listener
    }

    fun showChoiceDialog(@LayoutRes layout: Int) {
        dialog.setContentView(layout)
        dialog.findViewById<TextView>(R.id.tv_dialog_title).text = title
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        dialog.show()

        dialog.findViewById<TextView>(R.id.tv_dialog_no).setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.tv_dialog_yes).setOnClickListener {
            onClickedListener.onClicked(1)
            dialog.dismiss()
        }
    }

    fun showOneChoiceDialog(@LayoutRes layout: Int) {
        dialog.setContentView(layout)
        dialog.findViewById<TextView>(R.id.tv_dialog_title).text = title
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        dialog.show()
        dialog.setCancelable(false)
        dialog.findViewById<TextView>(R.id.tv_dialog_answer).setOnClickListener {
            dialog.dismiss()

        }
    }

    // 번개 신청취소 다이어로그
    fun showConfirmDialog(@LayoutRes layout: Int) {
        dialog.setContentView(layout)
        dialog.findViewById<TextView>(R.id.tv_dialog_title).text = title
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        dialog.show()

        dialog.findViewById<TextView>(R.id.tv_dialog_check).setOnClickListener {
//            onClickedListener.onClicked(1)
            dialog.dismiss()
            (context as ApplyThunderDetailActivity).finish()

        }
    }

    //번개 삭제 다이어로그
    fun showDeleteDialog(@LayoutRes layout: Int) {
        dialog.setContentView(layout)
        dialog.findViewById<TextView>(R.id.tv_dialog_title).text = title
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        dialog.show()

        dialog.findViewById<TextView>(R.id.tv_dialog_check).setOnClickListener {
//            onClickedListener.onClicked(1)
            dialog.dismiss()
            (context as OpenThunderDetailActivity).finish()
        }
    }


    //번개 삭제 다이어로그
    fun showSignDialog(@LayoutRes layout: Int) {
        dialog.setContentView(layout)
        dialog.findViewById<TextView>(R.id.tv_dialog_title).text = title
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        dialog.show()

        dialog.findViewById<TextView>(R.id.tv_dialog_check).setOnClickListener {
//            onClickedListener.onClicked(1)
            dialog.dismiss()
            (context as SignInActivity).finish()
        }
    }


}