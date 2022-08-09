package com.playtogether_android.app.util

import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.playtogether_android.app.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("subwayLine")
    fun subwayLine(imageView: ImageView, name: String) {
        when (name) {
            "01호선" -> imageView.setImageResource(R.drawable.sub_line_1)
            "02호선" -> imageView.setImageResource(R.drawable.sub_line_2)
            "03호선" -> imageView.setImageResource(R.drawable.sub_line_3)
            "04호선" -> imageView.setImageResource(R.drawable.sub_line_4)
            "05호선" -> imageView.setImageResource(R.drawable.sub_line_5)
            "06호선" -> imageView.setImageResource(R.drawable.sub_line_6)
            "07호선" -> imageView.setImageResource(R.drawable.sub_line_7)
            "08호선" -> imageView.setImageResource(R.drawable.sub_line_8)
            "09호선" -> imageView.setImageResource(R.drawable.sub_line_9)
            "우이신설경전철" -> imageView.setImageResource(R.drawable.sub_wooe)
            "북한산우이" -> imageView.setImageResource(R.drawable.sub_wooe)
            "김포공항" -> imageView.setImageResource(R.drawable.sub_gimpo_gold)
            "김포도시철도" -> imageView.setImageResource(R.drawable.sub_gimpo_gold)
            "수인분당선" -> imageView.setImageResource(R.drawable.sub_shinbundang)
            "서해선" -> imageView.setImageResource(R.drawable.sub_west_sea)
            "경의선" -> imageView.setImageResource(R.drawable.sub_gyeongui_center)
            else -> imageView.setImageResource(R.drawable.sub_incheon_2)
        }
    }
}

@BindingAdapter("app:imageSelecter")
fun bindImageSelecter(editText: EditText, imageView: ImageView) {
    if (editText.text.isNullOrEmpty()) {
        imageView.setImageResource(R.drawable.ic_icn_message)
    } else {
        imageView.setImageResource(R.drawable.ic_icn_message_black)
    }
}

