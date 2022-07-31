package com.playtogether_android.app.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemHomeNewBinding
import com.playtogether_android.domain.model.light.HomeLightningData

class HomeNewAdapter : ListAdapter<HomeLightningData, HomeNewAdapter.ViewHolder>(HomeComparator()) {
    inner class ViewHolder(private val binding: ItemHomeNewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: HomeLightningData) {
            with(binding) {
                tvHomenewTitle.text = item.title
                tvHomenewDate.text = "${item.date} ${item.place} ${item.time}"
                tvHomenewPeopleCnt.text = "${item.lightMemberCnt}/${item.peopleCnt}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeNewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeNewAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    class HomeComparator : DiffUtil.ItemCallback<HomeLightningData>() {
        override fun areItemsTheSame(oldItem: HomeLightningData, newItem: HomeLightningData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeLightningData, newItem: HomeLightningData): Boolean {
            return oldItem == newItem
        }

    }

}