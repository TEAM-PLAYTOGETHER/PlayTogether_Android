package com.playtogether_android.app.presentation.ui.thunder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemThunderApplicantListBinding
import com.playtogether_android.domain.model.home.ThunderJoinEndMember
import com.playtogether_android.domain.model.thunder.Member

class ApplicantListAdapter : RecyclerView.Adapter<ApplicantListAdapter.ApplicantListViewHolder>() {

//    private val _applicantList = mutableListOf<TempApplicantData.UserList>()
//    var applicantList: List<TempApplicantData.UserList> = _applicantList

    val applicantList = mutableListOf<Member>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApplicantListViewHolder {
        val binding = ItemThunderApplicantListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ApplicantListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApplicantListViewHolder, position: Int) {
        holder.onBind(applicantList[position])
    }

    override fun getItemCount(): Int = applicantList.size

    class ApplicantListViewHolder(private val binding: ItemThunderApplicantListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Member) {
            binding.member = data
//            binding.tvApplicantName.text = data.name
//            binding.tvApplicantAge.text = data.age.toString()
//            binding.tvApplicantMbti.text = data.mbti
        }

    }
}