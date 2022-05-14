package com.playtogether_android.app.presentation.ui.thunder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ItemThunderListBinding
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListAdapter
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.domain.model.thunder.ThunderTabListData

class ThunderListAdapter : RecyclerView.Adapter<ThunderListAdapter.ThunderListViewHolder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int, thunderId: Int)
    }

    var itemClick: ItemClick? = null

    private val _thunderList = mutableListOf<ThunderTabListData.Data>()

    var thunderList: List<ThunderTabListData.Data> = _thunderList
        set(value) {
            _thunderList.clear()
            _thunderList.addAll(value)
            notifyDataSetChanged()
        }

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
            holder.binding.llThunderlistItemContainer.setOnClickListener(View.OnClickListener {
                itemClick?.onClick(it, position, item.lightId)
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
            binding.ivThunderlistImage.setImageResource(setImageView(data.category))
            //여기서 먹갈할 카테고리를 몰라서 먹갈할 구분이 불가능할거같은데?
        }

        private fun setImageView(category: String): Int {
            when (category) {
                ThunderCategoryListAdapter.CATEGORY_EAT -> return R.drawable.img_eat_list
                ThunderCategoryListAdapter.CATEGORY_GO -> return R.drawable.img_go_list
                ThunderCategoryListAdapter.CATEGORY_DO -> return R.drawable.img_do_list
                else -> return -1
            }
        }
    }
}