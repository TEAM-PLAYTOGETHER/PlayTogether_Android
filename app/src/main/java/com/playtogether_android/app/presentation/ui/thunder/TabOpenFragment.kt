package com.playtogether_android.app.presentation.ui.thunder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentTabOpenBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderViewModel
import com.playtogether_android.domain.model.thunder.ThunderTabListData
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.playtogether_android.app.presentation.ui.thunder.adapter.ThunderTabListAdapter
import com.playtogether_android.app.util.SpaceItemDecoration
import timber.log.Timber

@AndroidEntryPoint
class TabOpenFragment : BaseFragment<FragmentTabOpenBinding>(R.layout.fragment_tab_open) {

    private lateinit var thunderListAdapter: ThunderTabListAdapter

    private val thunderViewModel: ThunderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = thunderViewModel
        initThunderListAdapter()
//        getOpenList()
//        observeOpenList()
    }


    private fun initThunderListAdapter() {
        thunderListAdapter = ThunderTabListAdapter()
        thunderViewModel.thunderOpenList.observe(viewLifecycleOwner) {
            thunderListAdapter.submitList(it)
        }
        with(binding.rvOpenThunderList) {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = thunderListAdapter
            addItemDecoration(SpaceItemDecoration(0, 10, 0, 0))
        }
        //리스트 클릭시 오픈 상세뷰로 이동
//        thunderListAdapter.itemClick = object : ThunderListAdapter.ItemClick {
//            override fun onClick(view: View, position: Int, thunderId: Int) {
//                val intent = Intent(context, OpenThunderDetailActivity::class.java)
//                intent.putExtra("thunderId", thunderId)
//                startActivity(intent)
//            }
//        }
    }

//    private fun getOpenList() {
//        thunderViewModel.getOpenList()
//    }

//    private fun observeOpenList() {
//        thunderViewModel.thundertabListData.observe(viewLifecycleOwner) {
//            val thunderTabListData = mutableListOf<ThunderTabListData.Data>()
//            thunderTabListData.addAll(it.data)
//            thunderListAdapter.thunderList = thunderTabListData
//
//            Log.d("connect-test", it.toString())
//
//            // 오픈한 리스트 정렬(임시)
//            thunderTabListData.sortBy { it.date }
//        }
//    }


}