package com.playtogether_android.app.presentation.ui.thunder.list.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ItemThunderListBinding
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity
import com.playtogether_android.app.util.ListComparator
import com.playtogether_android.app.util.stringListBuilder
import com.playtogether_android.domain.model.light.CategoryData

class ThunderCategoryListItemAdapter :
    ListAdapter<CategoryData, ThunderCategoryListItemAdapter.ViewHolder>(ListComparator<CategoryData>()) {
    inner class ViewHolder(private val binding: ItemThunderListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CategoryData) {
            with(binding) {
                categoryData = data
                tvThunderItemDate.text =
                    itemView.context.stringListBuilder(
                        itemView.context,
                        listOf(data.date, data.place, data.time)
                    )
                tvThunderItemLimitCount.text =
                    itemView.context.stringListBuilder(
                        itemView.context,
                        listOf(
                            PERSON,
                            data.lightMemberCnt.toString(),
                            " / ",
                            data.peopleCnt.toString()
                        )
                    )
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, ThunderDetailActivity::class.java)
                    intent.putExtra("thunderId", data.lightId)
                    Log.d("TestThunderId", "" + data.lightId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    private fun setImageView(category: String): Int {
        when (category) {
            CATEGORY_EAT -> return R.drawable.img_eat_list
            CATEGORY_GO -> return R.drawable.img_go_list
            CATEGORY_DO -> return R.drawable.img_do_list
            else -> return -1
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
        holder.onBind(getItem(position))
    }

    companion object {
        const val PERSON = "인원 "
        const val CATEGORY_EAT = "먹을래"
        const val CATEGORY_GO = "갈래"
        const val CATEGORY_DO = "할래"
    }

}