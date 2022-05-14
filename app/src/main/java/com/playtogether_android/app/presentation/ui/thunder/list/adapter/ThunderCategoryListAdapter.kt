package com.playtogether_android.app.presentation.ui.thunder.list.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ItemThunderListBinding
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity
import com.playtogether_android.domain.model.light.CategoryData

class ThunderCategoryListAdapter :
    ListAdapter<CategoryData, ThunderCategoryListAdapter.ViewHolder>(ListComparator()) {
    inner class ViewHolder(private val binding: ItemThunderListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CategoryData) {
            with(binding) {
                tvThunderItemTitle.text = data.title
                tvThunderItemDate.text =
                    stringBuilder(
                        itemView.context,
                        listOf("${data.date} ", "${data.place} ", data.time)
                    )
                tvThunderItemLimitCount.text =
                    stringBuilder(
                        itemView.context,
                        listOf(
                            PERSON,
                            data.lightMemberCnt.toString(),
                            " / ",
                            data.peopleCnt.toString()
                        )
                    )

                ivThunderlistImage.setImageResource(setImageView(data.category))

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

    private fun stringBuilder(context: Context, stringList: List<String>): String {
        val sb = StringBuilder()

        for (it in stringList) {
            if (it == "-1")
                sb.append(context.getString(R.string.createthunder_infinite))
            else {
                sb.append(it)
            }
        }
        return sb.toString()
    }

    companion object {
        const val PERSON = "인원 "
        const val CATEGORY_EAT = "먹을래"
        const val CATEGORY_GO = "갈래"
        const val CATEGORY_DO = "할래"
    }

}