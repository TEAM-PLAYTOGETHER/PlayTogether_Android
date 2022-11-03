package com.playtogether_android.app.presentation.ui.thunder.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemThunderListBinding
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.SCRAP_DEFAULT
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.SCRAP_MINUS
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.SCRAP_PLUS
import com.playtogether_android.domain.model.light.CategoryData

class ThunderCategoryListItemAdapter(
    private val itemClick: (Int, Int) -> Unit
) :
    RecyclerView.Adapter<ThunderCategoryListItemAdapter.ThunderCategoryListViewHolder>() {

    private val thunderList = mutableListOf<CategoryData>()

    class ThunderCategoryListViewHolder(
        private val binding: ItemThunderListBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CategoryData) {
            binding.categoryData = data
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ThunderCategoryListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemThunderListBinding.inflate(layoutInflater, parent, false)
        return ThunderCategoryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThunderCategoryListViewHolder, position: Int) {
        val item = thunderList[position]
        holder.onBind(item)
        holder.itemView.setOnClickListener {
            itemClick(item.lightId, position)
        }
    }

    override fun getItemCount(): Int = thunderList.size

    fun initList(list: List<CategoryData>) {
        thunderList.clear()
        thunderList.addAll(list)
        notifyDataSetChanged()
    }

    fun addList(list: List<CategoryData>) {
        val startPosition = thunderList.size
        val endPosition = list.size - 1
        val addedList = list.subList(startPosition, endPosition)
        thunderList.addAll(addedList)
        notifyItemRangeInserted(startPosition, addedList.size)
    }

    fun updateScrapCount(position: Int, op: String) {
        when (op) {
            SCRAP_PLUS -> thunderList[position].likeCount++
            SCRAP_MINUS -> thunderList[position].likeCount--
            SCRAP_DEFAULT -> return
        }
        notifyItemChanged(position)
    }
}