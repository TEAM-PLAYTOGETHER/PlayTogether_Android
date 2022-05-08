//package com.playtogether_android.app.presentation.ui.home.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.playtogether_android.app.databinding.ItemHomeHotBinding
//
//class HomeHotAdapter(
//
//) : ListAdapter<TempHotData, HomeHotAdapter.ViewHolder>(HomeComparator()) {
//    inner class ViewHolder(private val binding: ItemHomeHotBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun onBind(item: TempHotData) {
//
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHotAdapter.ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = ItemHomeHotBinding.inflate(layoutInflater, parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: HomeHotAdapter.ViewHolder, position: Int) {
//        val item = getItem(position)
//        holder.onBind(item)
//    }
//
//    class HomeComparator : DiffUtil.ItemCallback<TempHotData>() {
//        override fun areItemsTheSame(oldItem: TempHotData, newItem: TempHotData): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: TempHotData, newItem: TempHotData): Boolean {
//            return oldItem == newItem
//        }
//
//    }
//
//}