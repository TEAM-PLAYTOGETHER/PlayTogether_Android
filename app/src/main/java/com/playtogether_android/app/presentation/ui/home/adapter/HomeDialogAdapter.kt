package com.playtogether_android.app.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ItemHomeDialogBinding
import com.playtogether_android.app.presentation.ui.home.view.HomeFragmentDialog
import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.playtogether_android.app.util.ListAdapterComparator
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.onboarding.CrewListData
import timber.log.Timber

class HomeDialogAdapter(
    val dialog: HomeFragmentDialog
) :
    ListAdapter<CrewListData.Data.CrewList, HomeDialogAdapter.ViewHolder>
        (ListAdapterComparator<CrewListData.Data.CrewList>()) {
    inner class ViewHolder(private val binding: ItemHomeDialogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CrewListData.Data.CrewList) {
            binding.tvItemhomeCrew.apply {
                text = data.name
                setTextAppearance(crewTextStyle(data.name))
            }
            binding.tvItemhomeCrewCode.apply {
                text = "코드 : ${data.crewCode}"
                setTextAppearance(crewCodeTextStyle(data.crewCode))
            }
            itemView.setOnClickListener {
                //todo 이후 hot/new 리스트 갱신을 할 때 crweId를 받는다 preference에 가지고 있는 id가 아닌
//                //todo 다른 id를 클릭한다면 homeViewModel(crewId)로 갱신해야 한다. -> dismiss()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeDialogBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
        holder.itemView.setOnClickListener {
            dialog.setChangeCrew(item.id, item.name)
        }
    }

    private fun crewTextStyle(crewName: String): Int {
        return if (PlayTogetherRepository.crewName.equals(crewName))
            R.style.home_select_item_crew
        else
            R.style.home_unselect_item_crew
    }

    private fun crewCodeTextStyle(crewName: String): Int {
        return if (PlayTogetherRepository.crewName.equals(crewName))
            R.style.home_select_item_crewCode
        else
            R.style.home_unselect_item_crewCode
    }
}