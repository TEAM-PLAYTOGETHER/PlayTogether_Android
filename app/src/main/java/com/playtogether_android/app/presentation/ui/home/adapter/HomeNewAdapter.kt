package com.playtogether_android.app.presentation.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemHomeNewBinding
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity
import com.playtogether_android.app.util.ListAdapterComparator
import com.playtogether_android.app.util.homeTabTextBuilder
import com.playtogether_android.domain.model.light.CategoryData
import timber.log.Timber

class HomeNewAdapter :
    ListAdapter<CategoryData, HomeNewAdapter.ViewHolder>(ListAdapterComparator<CategoryData>()) {

    interface ItemClick {
        fun onClick(view: View, position: Int, thunderId: Int)
    }

    var itemClick: ItemClick? = null

    inner class ViewHolder(private val binding: ItemHomeNewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CategoryData) {
            with(binding) {
                categoryData = item
                val context = itemView.context
                val dateChange = item.date?.replace("-",".")
                tvHomenewDate.text =
                    context.homeTabTextBuilder(
                        context,
                        listOf(dateChange, " ", item.place, " ", item.time)
                    )
                tvHomenewPeopleCnt.text = context.homeTabTextBuilder(
                    context,
                    listOf(item.lightMemberCnt.toString(), "/", item.peopleCnt.toString())
                )
                Timber.e("item date : ${item.date}")
                Timber.e("item place : ${item.place}")
                Timber.e("item time : ${item.time}")
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
        if (itemClick != null) {
            holder.itemView.setOnClickListener {
                itemClick?.onClick(it, position, item.lightId)
            }
        }
    }

}