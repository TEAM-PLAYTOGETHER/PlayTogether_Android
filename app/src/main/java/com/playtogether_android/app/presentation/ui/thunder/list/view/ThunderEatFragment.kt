package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentThunderEatBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListItemAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderViewModel
import com.playtogether_android.app.util.SpaceItemDecorationVertical
import timber.log.Timber

class ThunderEatFragment : BaseFragment<FragmentThunderEatBinding>(R.layout.fragment_thunder_eat) {
    private lateinit var listAdapter: ThunderCategoryListItemAdapter
    private val thunderListViewModel: ThunderListViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("111 eat")
    }

    private fun initData() {
        thunderListViewModel.getLightCategoryList(ThunderListViewModel.CATEGORY_GO)
//        thunderListViewModel.setCategory(ThunderListActivity.CATEGORY_EAT)
    }

    private fun initView() {
//        initData()
        initAdapter()
    }

    //먹/갈/할 같이 사용할 메서드
    private fun initAdapter() {
        listAdapter = ThunderCategoryListItemAdapter()

        with(thunderListViewModel) {
            categoryEatList.observe(viewLifecycleOwner) { it ->
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