package com.playtogether_android.app.presentation.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemRedownOnboardingListBinding
import com.playtogether_android.domain.model.onboarding.CrewListData


class OnboardingReDownAdapter() :
    RecyclerView.Adapter<OnboardingReDownAdapter.OnboardingListViewHolder>() {
    var dataList = mutableListOf<CrewListData.Data.CrewList>()
    private var selectedPosition = -1

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


        if (selectedPosition === position) {
            holder.itemView.isSelected = true //using selector drawable
            holder.binding.ivSecondOnboardingJoinSelect.visibility = View.VISIBLE
        } else {
            holder.itemView.isSelected = false
            holder.binding.ivSecondOnboardingJoinSelect.visibility = View.INVISIBLE
        }

        holder.itemView.setOnClickListener { v ->
            if (selectedPosition >= 0) notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            itemClickListener.onClick(v, position)
            notifyItemChanged(selectedPosition)
        }

//        holder.itemView.setOnClickListener {
//            itemClickListener.onClick(it, position)
//        }
    }

    override fun getItemCount(): Int = dataList.size

    class OnboardingListViewHolder(
        val binding: ItemRedownOnboardingListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CrewListData.Data.CrewList) {
            binding.apply {
                crew = data


                executePendingBindings()
            }
        }
    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener


    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun setCrewList(data: MutableList<CrewListData.Data.CrewList>) {
        this.dataList = data
        notifyDataSetChanged()
    }

}