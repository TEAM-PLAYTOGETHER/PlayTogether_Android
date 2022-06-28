package com.playtogether_android.app.presentation.ui.thunder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentTabLikeBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderViewModel
import com.playtogether_android.domain.model.thunder.ThunderTabListData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabLikeFragment : BaseFragment<FragmentTabLikeBinding>(R.layout.fragment_tab_like) {

    private lateinit var thunderListAdapter: ThunderListAdapter

    private val thunderViewModel: ThunderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initThunderListAdapter()
        getLikeList()
        observeLikeList()
    }

    private fun initThunderListAdapter() {
        thunderListAdapter = ThunderListAdapter()
        with(binding.rvLikeThunderList) {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = thunderListAdapter
        }
        //리스트 클릭시 오픈 상세뷰로 이동
        thunderListAdapter.itemClick = object : ThunderListAdapter.ItemClick {
            override fun onClick(view: View, position: Int, thunderId: Int) {
                val intent = Intent(context, ThunderDetailActivity::class.java)
                intent.putExtra("thunderId", thunderId)
                startActivity(intent)
            }
        }
    }

    private fun getLikeList() {
        thunderViewModel.getLikeList()
    }

    private fun observeLikeList() {
        thunderViewModel.thundertabListData.observe(viewLifecycleOwner) {
            val thunderTabListData = mutableListOf<ThunderTabListData.Data>()
            thunderTabListData.addAll(it.data)
            thunderListAdapter.thunderList = thunderTabListData

            Log.d("connect-test", it.toString())

            // 찜한 리스트 정렬(임시)
            thunderTabListData.sortBy { it.date }
        }
    }


}