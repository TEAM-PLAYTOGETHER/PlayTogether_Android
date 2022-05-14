package com.playtogether_android.app.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemHomeApplicantListBinding
import com.playtogether_android.app.databinding.ItemMembersBinding
import com.playtogether_android.domain.model.home.ThunderJoinEndMember

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.ApplicantListViewHolder>() {

    val applicantList = mutableListOf<ThunderJoinEndMember>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApplicantListViewHolder {
        val binding = ItemMembersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ApplicantListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApplicantListViewHolder, position: Int) {
        holder.onBind(applicantList[position])
    }

    override fun getItemCount(): Int = applicantList.size

    class ApplicantListViewHolder(private val binding: ItemMembersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ThunderJoinEndMember) {
            binding.member = data
        }

    }
}