package com.playtogether_android.app.presentation.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemHomeHotBinding
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity
import com.playtogether_android.app.util.ListAdapterComparator
import com.playtogether_android.app.util.stringListBuilder
import com.playtogether_android.domain.model.light.CategoryData
import timber.log.Timber

class HomeHotAdapter :
    ListAdapter<CategoryData, HomeHotAdapter.ViewHolder>(ListAdapterComparator<CategoryData>()) {

    interface ItemClick {
        fun onClick(view: View, position: Int, thunderId: Int)
    }

    var itemClick: ItemClick? = null

    inner class ViewHolder(private val binding: ItemHomeHotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CategoryData) {
            with(binding) {
                val context = itemView.context
                categoryData = item
                tvHomenewDate.text =
                    context.stringListBuilder(
                        context,
                        listOf(item.date, " ", item.place, " ", item.time)
                    )
                tvHomenewPeopleCnt.text = context.stringListBuilder(
                    context,
                    listOf(item.lightMemberCnt.toString(), "/", item.peopleCnt.toString())
                )
                Timber.e("item date : ${item.date}")
                Timber.e("item place : ${item.place}")
                Timber.e("item time : ${item.time}")
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

        if (itemClick != null) {
            holder.itemView.setOnClickListener {
                itemClick?.onClick(it, position, item.lightId)
            }
        }
    }
}