package com.playtogether_android.app.presentation.ui.userInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemBlockedUserListBinding
import com.playtogether_android.domain.model.userInfo.BlockUserList

class BlockedUserAdapter : RecyclerView.Adapter<BlockedUserAdapter.BlockedUserViewHolder>() {

    // 유저 차단 해제 API에 필요한 blockUserId값 -> ManageBlockedUserActivity로 넘기기 위한 고차함수
    private var isMemberId: ((Int) -> Unit)? = null

    fun setIsMemberId(listener: (Int) -> Unit) {
        isMemberId = listener
    }

    private val _blockUserList = mutableListOf<BlockUserList.Block>()

    var blockUserList: List<BlockUserList.Block> = _blockUserList
    set(value) {
        _blockUserList.clear()
        _blockUserList.addAll(value)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BlockedUserViewHolder {
        val binding = ItemBlockedUserListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return BlockedUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BlockedUserViewHolder, position: Int) {
        holder.onBind(blockUserList[position])
    }

    override fun getItemCount(): Int = blockUserList.size

    inner class BlockedUserViewHolder(val binding: ItemBlockedUserListBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun onBind(data: BlockUserList.Block) {
                binding.blockUserList = data

                //todo 차단해제를 위한 해당 blockUserId값 ManageBlockedUserActivity로 전달
                binding.btnUnblock.setOnClickListener {
                    // 해당 blockUserId값 차단유저 Activity로 전달
                    isMemberId?.invoke(data.blockUserId!!)
                }
            }
        }
}