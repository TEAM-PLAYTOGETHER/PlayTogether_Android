package com.playtogether_android.app.presentation.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemSearchThunderListBinding
import com.playtogether_android.domain.model.search.SearchData

class SearchListAdapter :
    ListAdapter<SearchData.LightData, SearchListAdapter.SearchViewHolder>(SearchComparator()) {

    class SearchViewHolder(private val binding: ItemSearchThunderListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SearchData.LightData) {
            binding.model = data
        }
    }

    class SearchComparator() : DiffUtil.ItemCallback<SearchData.LightData>() {
        override fun areItemsTheSame(
            oldItem: SearchData.LightData,
            newItem: SearchData.LightData
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchData.LightData,
            newItem: SearchData.LightData
        ): Boolean {
            return oldItem.lightId == newItem.lightId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchThunderListBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}