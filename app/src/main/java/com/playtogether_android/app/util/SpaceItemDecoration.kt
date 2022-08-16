package com.playtogether_android.app.util

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    val top: Int,
    val bottom: Int,
    val left: Int,
    val right: Int,
) : RecyclerView.ItemDecoration() {
    fun Int.toPx() = (this * Resources.getSystem().displayMetrics.density).toInt()
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        if (parent.getChildAdapterPosition(view) % 2 == 0) {

        }

        outRect.top = top.toPx()
        outRect.bottom = bottom.toPx()
        outRect.left = left.toPx()
        outRect.right = right.toPx()
    }
}


class SpacesItemDecorationPhoto : RecyclerView.ItemDecoration() {
    fun Int.toPx() = (this * Resources.getSystem().displayMetrics.density).toInt()
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val betweenSpacePx = BETWEEN_SPACE.toPx()
        val bottomSpacePx = BOTTOM_SPACE.toPx()
        val betweenMiddleSpacePx = BETWEEN_SPACE_MIDDLE.toPx()

        if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.left = betweenSpacePx
            outRect.right = betweenMiddleSpacePx
        } else {
            outRect.right = betweenSpacePx
            outRect.left =betweenMiddleSpacePx
        }
        outRect.bottom = bottomSpacePx
    }
    companion object {
        private const val BETWEEN_SPACE = 8
        private const val BOTTOM_SPACE = 8
        private const val BETWEEN_SPACE_MIDDLE = 8
    }
}