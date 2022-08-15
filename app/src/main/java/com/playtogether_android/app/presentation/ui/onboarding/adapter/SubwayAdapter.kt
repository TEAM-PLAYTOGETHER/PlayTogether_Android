package com.playtogether_android.app.presentation.ui.onboarding.adapter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemOnboardingSubwayBinding
import com.playtogether_android.domain.model.onboarding.SubwayListData


class SubwayAdapter:
    RecyclerView.Adapter<SubwayAdapter.OnboardingListViewHolder>() {
    var dataList = mutableListOf<SubwayListData>()
    var findText = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingListViewHolder {
        val binding = ItemOnboardingSubwayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnboardingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingListViewHolder, position: Int) {
        holder.onBind(dataList[position], colorChange(dataList[position].STATION_NM, findText))
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = dataList.size

    inner class OnboardingListViewHolder(
        val binding: ItemOnboardingSubwayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: SubwayListData, text: SpannableStringBuilder) {
            binding.apply {
                tvSubwayName.text = text
                subway = data
                executePendingBindings()
            }
        }
    }


    private fun colorChange(fulltext: String, findText: String): SpannableStringBuilder {
        val str = SpannableStringBuilder(fulltext)
        val startInt = fulltext.indexOf(findText)
        val endInt = startInt + findText.length
        str.setSpan(
            ForegroundColorSpan(Color.parseColor("#2C77D4")),
            startInt,
            endInt,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return str
    }


    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener


    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener

    }


    fun setCrewList(data: MutableList<SubwayListData>) {
        this.dataList = data
        notifyDataSetChanged()
    }
}