package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentThunderGoBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListItemAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderViewModel
import com.playtogether_android.app.util.SpaceItemDecoration

class ThunderGoFragment(val thunderViewModel: ThunderViewModel) : BaseFragment<FragmentThunderGoBinding>(R.layout.fragment_thunder_go) {
    private lateinit var listAdapter: ThunderCategoryListItemAdapter
//    private val thunderViewModel: ThunderViewModel by activityViewModels()
    private val thunderListViewModel: ThunderListViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.listViewModel = thunderListViewModel
        binding.lifecycleOwner = this

        initAdapter()
    }

    private fun initAdapter() {
        listAdapter = ThunderCategoryListItemAdapter(thunderViewModel, viewLifecycleOwner)
        with(thunderListViewModel) {
            categoryGoList.observe(viewLifecycleOwner) {
                listAdapter.submitList(it)
            }
        }

        with(binding.rvThundergoContainer) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpaceItemDecoration(0, 10, 0, 0))
            adapter = listAdapter
        }
    }

    private fun initData() {
        thunderListViewModel.getLightCategoryList(ThunderListViewModel.CATEGORY_GO)
    }
}
