package com.playtogether_android.app.presentation.ui.thunder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentTabApplyBinding
import com.playtogether_android.app.databinding.FragmentTabLikeBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class TabLikeFragment : BaseFragment<FragmentTabLikeBinding>(R.layout.fragment_tab_like) {

    private lateinit var thunderListAdapter: ThunderListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initAdapter()
    }

//    private fun initAdapter() {
//        thunderListAdapter = ThunderListAdapter()
//
//        thunderListAdapter.thunderList = listOf(
//            TempThunderListData.ThunderList("37", "신촌 곱창 선착 5명 구함", "2022.04.13", "14:00", 6, "신촌","1"),
//            TempThunderListData.ThunderList("37", "신촌 곱창 선착 5명 구함", "2022.04.13", "14:00", 6, "신촌","1"),
//            TempThunderListData.ThunderList("37", "신촌 곱창 선착 5명 구함", "2022.04.13", "14:00", 6, "신촌","1"),
//            TempThunderListData.ThunderList("37", "신촌 곱창 선착 5명 구함", "2022.04.13", "14:00", 6, "신촌","1"),
//            TempThunderListData.ThunderList("37", "신촌 곱창 선착 5명 구함", "2022.04.13", "14:00", 6, "신촌","1"),
//            TempThunderListData.ThunderList("37", "신촌 곱창 선착 5명 구함", "2022.04.13", "14:00", 6, "신촌","1"),
//            TempThunderListData.ThunderList("37", "신촌 곱창 선착 5명 구함", "2022.04.13", "14:00", 6, "신촌","1"),
//            TempThunderListData.ThunderList("37", "신촌 곱창 선착 5명 구함", "2022.04.13", "14:00", 6, "신촌","1"),
//
//            )
//
//        with(binding.rvLikeThunderList){
//            layoutManager = LinearLayoutManager(requireActivity())
//            adapter = thunderListAdapter
//        }
//
//        thunderListAdapter.notifyDataSetChanged()
//    }


}