package com.playtogether_android.app.presentation.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemRedownOnboardingListBinding
import com.playtogether_android.domain.model.onboarding.CrewListData

class OnboardingReDownAdapter():
    RecyclerView.Adapter<OnboardingReDownAdapter.OnboardingListViewHolder>() {
    var dataList = mutableListOf<CrewListData.Data.CrewList>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingListViewHolder {
        val binding = ItemRedownOnboardingListBinding.inflate(
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
        val binding: ItemRedownOnboardingListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data : CrewListData.Data.CrewList) {
            binding.apply {
                crew = data
                executePendingBindings()
            }
        }
    }

    fun setCrewList(data : MutableList<CrewListData.Data.CrewList>) {
        this.dataList = data
        notifyDataSetChanged()
    }

}