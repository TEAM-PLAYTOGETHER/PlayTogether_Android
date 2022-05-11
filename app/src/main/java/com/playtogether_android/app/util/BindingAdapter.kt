package com.playtogether_android.app.util

import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.playtogether_android.app.R

@BindingAdapter("app:imageSelecter")
fun bindImageSelecter(editText: EditText, imageView: ImageView) {
    if (editText.text.isNullOrEmpty()) {
        imageView.setImageResource(R.drawable.ic_icn_message)
    } else {
        imageView.setImageResource(R.drawable.ic_icn_message_black)
    }
}