package com.playtogether_android.app.util

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.playtogether_android.app.R

class CustomDialog(context:Context, val title:String) {
    private val dialog = Dialog(context)
    private lateinit var onClickedListener : ButtonClickListener

    interface ButtonClickListener{
        fun onClicked(num:Int)
    }
    fun setOnClickedListener(listener:ButtonClickListener){
        onClickedListener=listener
    }

    fun showChoiceDialog(@LayoutRes layout : Int){
        dialog.setContentView(layout)
        dialog.findViewById<TextView>(R.id.tv_dialog_title).text=title
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        dialog.show()

        dialog.findViewById<TextView>(R.id.tv_dialog_no).setOnClickListener{
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.tv_dialog_yes).setOnClickListener{
            onClickedListener.onClicked(1)
            dialog.dismiss()
        }
    }

    fun showOneChoiceDialog(@LayoutRes layout : Int){
        dialog.setContentView(layout)
        dialog.findViewById<TextView>(R.id.tv_dialog_title).text=title
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        dialog.show()

        dialog.findViewById<TextView>(R.id.tv_dialog_answer).setOnClickListener{
            dialog.dismiss()
        }
    }

    fun showConfirmDialog(@LayoutRes layout : Int){
        dialog.setContentView(layout)
        dialog.findViewById<TextView>(R.id.tv_dialog_title).text=title
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        dialog.show()

        dialog.findViewById<TextView>(R.id.tv_dialog_check).setOnClickListener{
//            onClickedListener.onClicked(1)
            dialog.dismiss()
        }
    }
}