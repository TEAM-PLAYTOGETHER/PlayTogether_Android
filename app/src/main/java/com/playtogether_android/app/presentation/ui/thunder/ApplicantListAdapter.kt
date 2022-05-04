package com.playtogether_android.app.presentation.ui.thunder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemThunderApplicantListBinding

class ApplicantListAdapter : RecyclerView.Adapter<ApplicantListAdapter.ApplicantListViewHolder>() {

    private val _applicantList = mutableListOf<TempApplicantData.UserList>()

    var applicantList: List<TempApplicantData.UserList> = _applicantList


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApplicantListViewHolder {
        val binding =  ItemThunderApplicantListBinding.inflate(
        LayoutInflater.from(parent.context),
        parent, false
        )
        return ApplicantListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApplicantListViewHolder, position: Int) {
        holder.onBind(applicantList[position])
    }

    override fun getItemCount(): Int = applicantList.size

    class ApplicantListViewHolder(private val binding : ItemThunderApplicantListBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun onBind(data : TempApplicantData.UserList) {
                binding.tvApplicantName.text = data.userId
                binding.tvApplicantAge.text = data.age.toString()
                binding.tvApplicantMbti.text = data.mbti
            }

    }
}