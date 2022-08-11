package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentThunderGoBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListItemAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import com.playtogether_android.app.util.SpaceItemDecorationVertical
import timber.log.Timber

class ThunderGoFragment : BaseFragment<FragmentThunderGoBinding>(R.layout.fragment_thunder_go) {
    private lateinit var listAdapter: ThunderCategoryListItemAdapter
    private val thunderListViewModel: ThunderListViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("111 : gogo")
        initView()
    }

    private fun initView() {
//        initData()
        initAdapter()
    }
    private fun initAdapter() {
        listAdapter = ThunderCategoryListItemAdapter()
        with(thunderListViewModel) {
            _categoryGoList.observe(viewLifecycleOwner) {
                listAdapter.submitList(it)
            }
        }

        with(binding.rvThundergoContainer) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpaceItemDecorationVertical())
            adapter = listAdapter
        }
    }

    private fun initData() {
        thunderListViewModel.getLightCategoryList(ThunderListViewModel.CATEGORY_GO)
    }
}
