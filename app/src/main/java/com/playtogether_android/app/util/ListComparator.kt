package com.playtogether_android.app.util

import androidx.recyclerview.widget.DiffUtil
import com.playtogether_android.domain.model.light.CategoryData


class ListComparator : DiffUtil.ItemCallback<CategoryData>() {
    override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
        return oldItem.lightId == newItem.lightId
    }

    override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
        return oldItem == newItem
    }
}