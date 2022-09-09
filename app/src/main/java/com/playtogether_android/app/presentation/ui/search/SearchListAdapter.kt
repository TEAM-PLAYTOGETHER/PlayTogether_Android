package com.playtogether_android.app.presentation.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemSearchThunderListBinding
import com.playtogether_android.domain.model.search.SearchData

class SearchListAdapter(private val clickThunderItem : (Int) -> Unit) :
    ListAdapter<SearchData.LightData, SearchListAdapter.SearchViewHolder>(SearchComparator()) {

    class SearchViewHolder(private val binding: ItemSearchThunderListBinding, private val clickThunderItem: (Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SearchData.LightData) {
            binding.model = data
            clickItem(data)
        }
        private fun clickItem(data : SearchData.LightData){
            itemView.setOnClickListener{
                clickThunderItem(data.lightId)
            }
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
        return SearchViewHolder(binding, clickThunderItem)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}