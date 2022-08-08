package com.playtogether_android.app.presentation.ui.onboarding.adapter

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemOnboardingSubwayBinding
import com.playtogether_android.domain.model.onboarding.SubwayData

class SearchInSubwayAdapter() :
    ListAdapter<SubwayData, SearchInSubwayAdapter.SearchIngredientsViewHolder>(
        SearchIngredientsComparator()
    ) {
    var findText = ""

    class SearchIngredientsViewHolder(private val binding: ItemOnboardingSubwayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(text: SpannableStringBuilder) {
            binding.tvSubwayName.text = text
            itemView.setOnClickListener {

            }
        }
    }

    class SearchIngredientsComparator() : DiffUtil.ItemCallback<SubwayData>() {
        override fun areItemsTheSame(
            oldItem: SubwayData,
            newItem: SubwayData
        ): Boolean {
            return oldItem.STATION_NM == newItem.STATION_NM
        }

        override fun areContentsTheSame(
            oldItem: SubwayData,
            newItem: SubwayData
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchIngredientsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemOnboardingSubwayBinding.inflate(layoutInflater, parent, false)
        return SearchIngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchIngredientsViewHolder, position: Int) {
        holder.onBind(makeBold(getItem(position).STATION_NM, findText))
    }

    fun makeBold(fulltext: String, findText: String): SpannableStringBuilder {
        val str = SpannableStringBuilder(fulltext)
        val startInt = fulltext.indexOf(findText)
        val endInt = startInt + findText.length
        str.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
            startInt,
            endInt,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return str
    }
}