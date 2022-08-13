package com.playtogether_android.app.presentation.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemHomeHotBinding
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity
import com.playtogether_android.domain.model.light.HomeLightningData

class HomeHotAdapter : ListAdapter<HomeLightningData, HomeHotAdapter.ViewHolder>(HomeComparator()) {
    inner class ViewHolder(private val binding: ItemHomeHotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: HomeLightningData) {
            with(binding) {
                tvHomenewTitle.text = item.title
                tvHomenewDate.text = "${item.date} ${item.place} ${item.time}"
                tvHomenewPeopleCnt.text = "${item.lightMemberCnt}/${item.peopleCnt}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHotAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeHotBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeHotAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
        holder.itemView.setOnClickListener {
            Intent(it.context, ThunderDetailActivity::class.java).apply {
                putExtra("thunderId", item.id)
                it.context.startActivity(this)
            }
        }
    }

    class HomeComparator : DiffUtil.ItemCallback<HomeLightningData>() {
        override fun areItemsTheSame(
            oldItem: HomeLightningData,
            newItem: HomeLightningData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HomeLightningData,
            newItem: HomeLightningData
        ): Boolean {
            return oldItem == newItem
        }
    }

}