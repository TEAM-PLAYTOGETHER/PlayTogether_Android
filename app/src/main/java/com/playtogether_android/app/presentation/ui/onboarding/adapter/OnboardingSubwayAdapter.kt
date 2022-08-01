package com.playtogether_android.app.presentation.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemOnboardingSubwayBinding
import com.playtogether_android.app.databinding.ItemRedownOnboardingListBinding
import com.playtogether_android.domain.model.onboarding.CrewListData
import com.playtogether_android.domain.model.onboarding.SubwayListData

class OnboardingSubwayAdapter():
    RecyclerView.Adapter<OnboardingSubwayAdapter.OnboardingSubwayViewHolder>() {
    var dataList = mutableListOf<SubwayListData>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingSubwayViewHolder {
        val binding = ItemOnboardingSubwayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnboardingSubwayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingSubwayViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    class OnboardingSubwayViewHolder(
        val binding: ItemOnboardingSubwayBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data : SubwayListData) {
            binding.apply {
                subway = data
                executePendingBindings()
            }
        }
    }

    fun setSubwayList(data : MutableList<SubwayListData>) {
        this.dataList = data
        notifyDataSetChanged()
    }

}