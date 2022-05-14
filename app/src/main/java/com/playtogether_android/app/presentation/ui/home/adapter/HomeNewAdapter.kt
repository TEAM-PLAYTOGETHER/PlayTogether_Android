package com.playtogether_android.app.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemHomeNewBinding
import com.playtogether_android.app.presentation.ui.home.temp.TempData

class HomeNewAdapter : ListAdapter<TempData, HomeNewAdapter.ViewHolder>(HomeComparator()) {
    inner class ViewHolder(private val binding: ItemHomeNewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: TempData) {
            with(binding) {
                tvHomenewTitle.text = item.title
                tvHomenewDate.text = item.date_tool
                tvHomenewPeopleCnt.text = item.people_cnt_tool
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

    class HomeComparator : DiffUtil.ItemCallback<TempData>() {
        override fun areItemsTheSame(oldItem: TempData, newItem: TempData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TempData, newItem: TempData): Boolean {
            return oldItem == newItem
        }

    }

}