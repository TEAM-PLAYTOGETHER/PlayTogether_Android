package com.playtogether_android.app.util

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.roundToInt

fun Context.getPageTransformer(): ViewPager2.PageTransformer {
    val compositePageTransformer = CompositePageTransformer()
    compositePageTransformer.addTransformer(MarginPageTransformer((20 * resources.displayMetrics.density).roundToInt()))

    return compositePageTransformer
}

fun Context.viewPagerAnimation(viewpager: ViewPager2) {
    val compositePageTransformer = getPageTransformer()
    with(viewpager) {
        clipToPadding = false
        clipChildren = false
        offscreenPageLimit = 1
        setPageTransformer(compositePageTransformer)
        setPadding(
            (20 * resources.displayMetrics.density).roundToInt(),
            0,
            (55 * resources.displayMetrics.density).roundToInt(),
            0
        )
        getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
}