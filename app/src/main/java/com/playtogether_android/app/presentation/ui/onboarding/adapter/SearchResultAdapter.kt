package com.playtogether_android.app.presentation.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemOnboardingSubwayBinding
import com.playtogether_android.domain.model.onboarding.SubwayListData

class SearchResultAdapter(private val itemClick: (SubwayListData) -> Unit) :
    ListAdapter<SubwayListData, SearchResultAdapter.SearchResultViewHolder>(SearchResultComparator()) {

    class SearchResultViewHolder(private val binding: ItemOnboardingSubwayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: SubwayListData, itemClick: (SubwayListData) -> Unit) {
            binding.subway = data
            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }

    class SearchResultComparator() : DiffUtil.ItemCallback<SubwayListData>() {
        override fun areItemsTheSame(oldItem: SubwayListData, newItem: SubwayListData): Boolean {
            return oldItem.STATION_NM == newItem.STATION_NM
        }

        override fun areContentsTheSame(oldItem: SubwayListData, newItem: SubwayListData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemOnboardingSubwayBinding.inflate(layoutInflater, parent, false)
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.onBind(getItem(position), itemClick)
    }
}