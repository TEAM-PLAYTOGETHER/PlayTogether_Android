package com.playtogether_android.app.presentation.ui.thunder.list.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemThunderListBinding
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity
import com.playtogether_android.app.util.ListAdapterComparator
import com.playtogether_android.domain.model.light.CategoryData

class ThunderCategoryListItemAdapter :
    ListAdapter<CategoryData, ThunderCategoryListItemAdapter.ViewHolder>(ListAdapterComparator<CategoryData>()) {
    inner class ViewHolder(private val binding: ItemThunderListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CategoryData) {
            binding.categoryData = data
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ThunderCategoryListItemAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemThunderListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ThunderCategoryListItemAdapter.ViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.onBind(item)
        val itemView = holder.itemView
        itemView.setOnClickListener {
            Intent(itemView.context, ThunderDetailActivity::class.java).apply {
                putExtra("thunderId", item.lightId)
                itemView.context.startActivity(this)
            }
        }
    }

    companion object {
        const val PERSON = "인원 "
    }
}