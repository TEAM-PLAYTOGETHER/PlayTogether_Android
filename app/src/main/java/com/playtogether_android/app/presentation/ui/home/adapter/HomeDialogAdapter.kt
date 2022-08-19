package com.playtogether_android.app.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ItemHomeDialogBinding
import com.playtogether_android.app.util.ListAdapterComparator
import com.playtogether_android.data.singleton.PlayTogetherRepository
import timber.log.Timber

class HomeDialogAdapter :
    ListAdapter<String, HomeDialogAdapter.ViewHolder>(ListAdapterComparator<String>()) {
    inner class ViewHolder(private val binding: ItemHomeDialogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(crew: String) {
            //todo homedialog에서 submitList 한 내역중에 현재 보여지는 동아리가 있으면 style 변경
            //todo 현재 보고 있는 동아리는 preference에 저장된 내역을 확인한다
            Timber.d("crewlist crew : $crew")
            binding.tvItemhomeCrew.text = crew
            Timber.d("crewlist view text : ${binding.tvItemhomeCrew.text}")
            binding.tvItemhomeCrew.apply {
                text = crew
                setTextAppearance(crewTextStyle(crew))
            }
            itemView.setOnClickListener {
                //todo 이후 hot/new 리스트 갱신을 할 때 crweId를 받는다 preference에 가지고 있는 id가 아닌
//                //todo 다른 id를 클릭한다면 homeViewModel(crewId)로 갱신해야 한다. -> dismiss()
//                homeViewModel.getHotList()
//                homeViewModel.getNewList()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeDialogBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private fun crewTextStyle(crewName: String): Int {
        return if (PlayTogetherRepository.crewName.equals(crewName))
            R.style.home_select_item_crew
        else
            R.style.home_unselect_item_crew
    }
}