package com.playtogether_android.app.presentation.ui.thunder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemThunderListBinding

class ThunderListAdapter : RecyclerView.Adapter<ThunderListAdapter.ThunderListViewHolder>() {

    private val _thunderList = mutableListOf<TempThunderListData.ThunderList>()

    var thunderList: List<TempThunderListData.ThunderList> = _thunderList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ThunderListViewHolder {
        val binding = ItemThunderListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ThunderListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThunderListViewHolder, position: Int) {
        holder.onBind(thunderList[position])
    }

    override fun getItemCount(): Int = thunderList.size


    class ThunderListViewHolder(private val binding : ItemThunderListBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun onBind(data: TempThunderListData.ThunderList) {
                binding.tvThunderItemTitle.text = data.title
                binding.tvThunderItemDate.text = data.date + data.place + data.time
                binding.tvThunderItemLimitCount.text = "인원" + data.lightMemberCnt + "/" + data.peopleCnt
            }
        }


}