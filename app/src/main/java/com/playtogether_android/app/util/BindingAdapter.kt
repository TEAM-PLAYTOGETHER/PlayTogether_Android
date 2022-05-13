package com.playtogether_android.app.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.playtogether_android.app.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("categoryImage")
    fun setCategoryImage(imageView: ImageView, category: String?) {
        with(imageView) {
            when(category.toString()) {
                "먹을래" -> {
                    setImageResource(R.drawable.img_eat)
                    visibility = View.VISIBLE
                }
                "갈래" -> {
                    setImageResource(R.drawable.img_go)
                    visibility = View.VISIBLE
                }
                "할래" -> {
                    setImageResource(R.drawable.img_do)
                    visibility = View.VISIBLE
                }

            }
        }
    }
}