package com.playtogether_android.app.presentation.ui.createThunder.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.playtogether_android.app.databinding.ItemCreateThunderPhotoBinding

class CreateThunderPhotoListAdapter(
    private val list: MutableList<Uri>
) :
    RecyclerView.Adapter<CreateThunderPhotoListAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemCreateThunderPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(uri: Uri) {
            with(binding) {
                Glide
                    .with(itemView.context)
                    .load(uri)
                    .into(ivCreatethunderPhoto)
                ivCreatethunderDelete.setOnClickListener {
                    notifyItemRemoved(bindingAdapterPosition)
                    list.removeAt(bindingAdapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCreateThunderPhotoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int = list.size
}