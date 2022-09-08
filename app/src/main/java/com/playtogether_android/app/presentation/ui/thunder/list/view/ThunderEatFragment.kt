package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentThunderEatBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListItemAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderViewModel
import com.playtogether_android.app.util.SpaceItemDecoration

class ThunderEatFragment(val thunderViewModel: ThunderViewModel) : BaseFragment<FragmentThunderEatBinding>(R.layout.fragment_thunder_eat) {
    private lateinit var listAdapter: ThunderCategoryListItemAdapter
//    private val thunderViewModel: ThunderViewModel by activityViewModels()
    private val thunderListViewModel: ThunderListViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listViewModel = thunderListViewModel
        binding.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        binding.listViewModel = thunderListViewModel
        binding.lifecycleOwner = this
        initAdapter()
    }

    //먹/갈/할 같이 사용할 메서드
    private fun initAdapter() {
        listAdapter = ThunderCategoryListItemAdapter(thunderViewModel,viewLifecycleOwner)

        with(thunderListViewModel) {
            categoryEatList.observe(viewLifecycleOwner) { it ->
                listAdapter.submitList(it)
            }
        }

        with(binding.rvThundereatContainer) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpaceItemDecoration(0, 10, 0, 0))
            adapter = listAdapter
        }
    }
}