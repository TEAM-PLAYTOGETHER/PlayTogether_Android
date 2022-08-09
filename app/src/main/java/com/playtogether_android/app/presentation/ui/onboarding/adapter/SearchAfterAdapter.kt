package com.playtogether_android.app.presentation.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemOnboardingSubwayBinding
import com.playtogether_android.domain.model.onboarding.CrewListData
import com.playtogether_android.domain.model.onboarding.SubwayListData

/*
class SearchAfterAdapter(private val itemClick: (SubwayListData) -> Unit) :
    ListAdapter<SubwayListData, SearchAfterAdapter.SearchAfterViewHolder>(SearchAfterComparator()) {
    var findText = ""

    class SearchAfterViewHolder(private val binding: ItemOnboardingSubwayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            data: SubwayListData,
            text: SpannableStringBuilder,
            itemClick: (SubwayListData) -> Unit
        ) {
            binding.tvSubwayName.text = text
            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }

    class SearchAfterComparator() : DiffUtil.ItemCallback<SubwayListData>() {
        override fun areItemsTheSame(oldItem: SubwayListData, newItem: SubwayListData): Boolean {
            return oldItem.STATION_NM == newItem.STATION_NM
        }

        override fun areContentsTheSame(oldItem: SubwayListData, newItem: SubwayListData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAfterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemOnboardingSubwayBinding.inflate(layoutInflater, parent, false)
        return SearchAfterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAfterViewHolder, position: Int) {
       holder.onBind(getItem(position), makeBold(getItem(position).STATION_NM, findText), itemClick)
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

 */

class SearchAfterAdapter() :
    RecyclerView.Adapter<SearchAfterAdapter.OnboardingListViewHolder>() {
    var dataList = mutableListOf<SubwayListData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingListViewHolder {
        val binding = ItemOnboardingSubwayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnboardingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingListViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    class OnboardingListViewHolder(
        val binding: ItemOnboardingSubwayBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: SubwayListData) {
            binding.apply {
                subway = data
                executePendingBindings()
            }
        }
    }

    fun setCrewList(data: MutableList<SubwayListData>) {
        this.dataList = data
        notifyDataSetChanged()
    }
}