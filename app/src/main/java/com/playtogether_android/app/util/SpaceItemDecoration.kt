package com.playtogether_android.app.util

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecorationVertical : RecyclerView.ItemDecoration() {
    fun Int.toPx() = (this * Resources.getSystem().displayMetrics.density).toInt()
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = BOTTOM_SPACE.toPx()
    }

    companion object {
        private const val BOTTOM_SPACE = 10
    }
}