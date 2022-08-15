package com.playtogether_android.app.presentation.ui.createThunder.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.playtogether_android.app.databinding.ItemCreateThunderPhotoBinding
import com.playtogether_android.app.presentation.ui.createThunder.CreateThunderViewModel
import timber.log.Timber

class CreateThunderPhotoListAdapter(val createThunderViewModel: CreateThunderViewModel) :
    RecyclerView.Adapter<CreateThunderPhotoListAdapter.ViewHolder>() {
    var mutablePhotoList = mutableListOf<Uri>()

    inner class ViewHolder(private val binding: ItemCreateThunderPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(uri: Uri) {
            with(binding) {
                Glide
                    .with(itemView.context)
                    .load(uri)
                    .into(ivCreatethunderPhoto)

                ivCreatethunderDelete.setOnClickListener {
                    setNewList(bindingAdapterPosition)
//                    notifyItemRemoved(bindingAdapterPosition)
                }
            }
        }
    }

    private fun setNewList(position: Int) {
        mutablePhotoList.removeAt(position) //현재 리스트 복사한다음에
        //지우려고 선택한 아이템을 현재 리스트에서 지우고
        //삭제할거하고 남은 대표카드 수정에 있는 카드의 id만 남겨서 뷰모델한테 전달
        Timber.e("rere adapter list : $mutablePhotoList")
        Timber.e("rere list count : ${mutablePhotoList.size}")
        createThunderViewModel.setPhotoList(mutablePhotoList)
        notifyDataSetChanged()
//        submitList(newList) //삭제한거 제거한 newlist를 리사이클러뷰에 다시 뿌림
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCreateThunderPhotoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(mutablePhotoList[position])
    }

    override fun getItemCount(): Int = mutablePhotoList.size
}