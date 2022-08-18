package com.playtogether_android.app.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemHomeDialogBinding
import com.playtogether_android.app.util.ListAdapterComparator

class HomeDialogAdapter :
    ListAdapter<String, HomeDialogAdapter.ViewHolder>(ListAdapterComparator<String>()) {
    inner class ViewHolder(private val binding: ItemHomeDialogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: String) {
            //todo homedialog에서 submitList 한 내역중에 현재 보여지는 동아리가 있으면 style 변경
            //todo 현재 보고 있는 동아리는 preference에 저장된 내역을 확인한다
            binding.tvItemhomeCrew.apply {
                text = data

                setOnClickListener {

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeDialogBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}