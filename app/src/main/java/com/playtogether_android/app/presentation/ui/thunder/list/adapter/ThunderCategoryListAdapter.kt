package com.playtogether_android.app.presentation.ui.thunder.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemThunderListBinding
import com.playtogether_android.domain.model.light.CategoryData
import java.lang.StringBuilder

class ThunderCategoryListAdapter :
    ListAdapter<CategoryData, ThunderCategoryListAdapter.ViewHolder>(ListComparator()) {
    inner class ViewHolder(private val binding: ItemThunderListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CategoryData) {
            with(binding) {
                tvThunderItemTitle.text = data.title
                tvThunderItemDate.text =
                    stringBuilder(listOf("${data.date} ", "${data.place} ", data.time))
                tvThunderItemLimitCount.text =
                    stringBuilder(
                        listOf(PERSON, data.lightMemberCnt, " / ", data.peopleCnt.toString())
                    )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ThunderCategoryListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemThunderListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThunderCategoryListAdapter.ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ListComparator : DiffUtil.ItemCallback<CategoryData>() {
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.lightId == newItem.lightId
        }

        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem == newItem
        }

    }

    private fun stringBuilder(stringList: List<String>): String {
        val sb = StringBuilder()

        for (it in stringList)
            sb.append(it)

        return sb.toString()
    }

    companion object {
        const val PERSON = "인원 "
    }

}