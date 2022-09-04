package com.playtogether_android.app.presentation.ui.thunder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemThunderListBinding
import com.playtogether_android.app.util.ListAdapterComparator
import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.model.thunder.ThunderTabListData

class ThunderTabListAdapter :
    ListAdapter<CategoryData, ThunderTabListAdapter.ViewHolder>(ListAdapterComparator<CategoryData>()) {
    inner class ViewHolder(private val binding: ItemThunderListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CategoryData) {
            binding.categoryData = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemThunderListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

}