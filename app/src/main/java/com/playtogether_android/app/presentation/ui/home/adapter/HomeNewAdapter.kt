package com.playtogether_android.app.presentation.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemHomeNewBinding
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity
import com.playtogether_android.app.util.ListComparator
import com.playtogether_android.app.util.stringListBuilder
import com.playtogether_android.domain.model.light.CategoryData

class HomeNewAdapter : ListAdapter<CategoryData, HomeNewAdapter.ViewHolder>(ListComparator<CategoryData>()) {
    inner class ViewHolder(private val binding: ItemHomeNewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CategoryData) {
            with(binding) {
                val context = itemView.context

                tvHomenewTitle.text = item.title
                tvHomenewDate.text =
                    context.stringListBuilder(context, listOf(item.date, item.place, item.time))
                tvHomenewPeopleCnt.text = context.stringListBuilder(
                    context,
                    listOf(item.lightMemberCnt.toString(), "/", item.peopleCnt.toString())
                )
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
        holder.itemView.setOnClickListener {
            Intent(it.context, ThunderDetailActivity::class.java).apply {
                putExtra("thunderId", item.lightId)
                it.context.startActivity(this)
            }
        }
        holder.onBind(item)
    }

    class HomeComparator : DiffUtil.ItemCallback<CategoryData>() {
        override fun areItemsTheSame(
            oldItem: CategoryData,
            newItem: CategoryData
        ): Boolean {
            return oldItem.lightId == newItem.lightId
        }

        override fun areContentsTheSame(
            oldItem: CategoryData,
            newItem: CategoryData
        ): Boolean {
            return oldItem == newItem
        }

    }

}