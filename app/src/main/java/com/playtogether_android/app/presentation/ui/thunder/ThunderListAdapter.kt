package com.playtogether_android.app.presentation.ui.thunder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemThunderListBinding
import com.playtogether_android.domain.model.thunder.ThunderTabListData

class ThunderListAdapter : RecyclerView.Adapter<ThunderListAdapter.ThunderListViewHolder>() {

    private val _thunderList = mutableListOf<ThunderTabListData.Data>()

    var thunderList: List<ThunderTabListData.Data> = _thunderList
        set(value) {
            _thunderList.clear()
            _thunderList.addAll(value)
            notifyDataSetChanged()
        }

    interface ItemClick {
        fun onClick(view: View, position: Int,thunderId : Int)
    }

    var itemClick: ItemClick? = null

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
        val item = _thunderList[position]
        holder.onBind(item)

        if (itemClick != null) {
            holder?.binding.llThunderlistItemContainer.setOnClickListener(View.OnClickListener {
                itemClick?.onClick(it, position,item.lightId)
            })
        }
    }

    override fun getItemCount(): Int = _thunderList.size


    class ThunderListViewHolder(val binding: ItemThunderListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ThunderTabListData.Data) {
            binding.tvThunderItemTitle.text = data.title
            binding.tvThunderItemDate.text = data.date + " " + data.place + " " + data.time
            binding.tvThunderItemLimitCount.text =
                "인원 " + data.lightMemberCnt + " / " + data.peopleCnt
//            Log.d("Adapter-connect", data.lightId)

//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, ApplyThunderDetailActivity::class.java)
//                intent.putExtra("thunderId", data.lightId)
//                itemView.context.shortToast("thunderId : ${data.lightId}")
//                itemView.context.startActivity(intent)
//            }


        }
    }

}

