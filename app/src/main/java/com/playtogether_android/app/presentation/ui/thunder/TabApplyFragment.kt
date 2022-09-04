package com.playtogether_android.app.presentation.ui.thunder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentTabApplyBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.thunder.adapter.ThunderTabListAdapter
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderViewModel
import com.playtogether_android.app.util.SpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TabApplyFragment : BaseFragment<FragmentTabApplyBinding>(R.layout.fragment_tab_apply) {

    private lateinit var thunderListAdapter: ThunderTabListAdapter

    private val thunderViewModel: ThunderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        initThunderListAdapter()
    }


    private fun initThunderListAdapter() {
        thunderListAdapter = ThunderTabListAdapter()
        thunderViewModel.thunderApplyList.observe(viewLifecycleOwner) {
            Timber.e("list size : ${thunderListAdapter.currentList.size}")

            thunderListAdapter.submitList(it)
        }
        with(binding.rvApplyThunderList) {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = thunderListAdapter
            addItemDecoration(SpaceItemDecoration(0, 10, 0, 0))
        }

        //리스트 클릭시 오픈 상세뷰로 이동
//        thunderListAdapter.itemClick = object : ThunderListAdapter.ItemClick {
//            override fun onClick(view: View, position: Int, thunderId: Int) {
//                val intent = Intent(context, ApplyThunderDetailActivity::class.java)
//                intent.putExtra("thunderId", thunderId)
//                startActivity(intent)
//            }
//        }
    }

//    private fun observeApplyList() {
//        thunderViewModel.thundertabListData.observe(viewLifecycleOwner) {
//            val thunderTabListData = mutableListOf<CategoryData>()
//            thunderTabListData.addAll(it.data)
//            thunderListAdapter.thunderList = thunderTabListData
//
//            Log.d("connect-test", it.toString())
//
//            // 신청한 리스트 정렬(임시)
//            thunderTabListData.sortBy { it.date }
//        }
//    }


}