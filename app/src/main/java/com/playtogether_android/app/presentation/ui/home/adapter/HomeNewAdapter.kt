package com.playtogether_android.app.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.databinding.ItemHomeNewBinding

class HomeNewAdapter(

) : ListAdapter<NewData, HomeNewAdapter.ViewHolder>(HomeComparator()) {
    inner class ViewHolder(private val binding: ItemHomeNewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: NewData) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeNewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeNewAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    class HomeComparator : DiffUtil.ItemCallback<NewData>() {
        override fun areItemsTheSame(oldItem: NewData, newItem: NewData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewData, newItem: NewData): Boolean {
            return oldItem == newItem
        }

    }

}