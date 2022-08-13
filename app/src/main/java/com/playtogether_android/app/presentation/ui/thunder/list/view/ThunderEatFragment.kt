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
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_EAT
import com.playtogether_android.app.util.SpaceItemDecorationVertical
import timber.log.Timber

class ThunderEatFragment : BaseFragment<FragmentThunderEatBinding>(R.layout.fragment_thunder_eat) {
    private lateinit var listAdapter: ThunderCategoryListItemAdapter
    private val thunderListViewModel: ThunderListViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listViewModel = thunderListViewModel
        binding.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        initAdapter()
    }

    //먹/갈/할 같이 사용할 메서드
    private fun initAdapter() {
        listAdapter = ThunderCategoryListItemAdapter()

        with(thunderListViewModel) {
            Timber.e("observe not : ${thunderListViewModel.categoryEatList.value}")
            categoryEatList.observe(viewLifecycleOwner) { it ->
                Timber.e("observe : ${thunderListViewModel.categoryEatList.value}")
                listAdapter.submitList(it)
            }
        }

        with(binding.rvThundereatContainer) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpaceItemDecorationVertical())
            adapter = listAdapter
        }
    }
}