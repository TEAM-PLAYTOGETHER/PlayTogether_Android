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
import com.playtogether_android.app.databinding.FragmentThunderDoBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListItemAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import com.playtogether_android.app.util.SpaceItemDecoration
import timber.log.Timber


class ThunderDoFragment : BaseFragment<FragmentThunderDoBinding>(R.layout.fragment_thunder_do) {
    private lateinit var listAdapter: ThunderCategoryListItemAdapter
    private val thunderListViewModel: ThunderListViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("111 do")
    }

    private fun initView() {
//        initData()
        initAdapter()
    }

    private fun initAdapter() {
        listAdapter = ThunderCategoryListItemAdapter()
        with(thunderListViewModel) {
            categoryDoList.observe(viewLifecycleOwner) {
                listAdapter.submitList(it)
            }
        }

        with(binding.rvThunderdoContainer) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpaceItemDecoration(0, 10, 0, 0))
            adapter = listAdapter
        }
    }

    private fun initData() {
        thunderListViewModel.getLightCategoryList(ThunderListViewModel.CATEGORY_DO)
    }
}
