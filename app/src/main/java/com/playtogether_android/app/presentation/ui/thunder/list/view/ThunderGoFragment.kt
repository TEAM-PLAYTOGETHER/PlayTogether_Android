package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentThunderGoBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListItemAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel

class ThunderGoFragment : BaseFragment<FragmentThunderGoBinding>(R.layout.fragment_thunder_go) {
    private val thunderListViewModel: ThunderListViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initData()
        ThunderEatFragment().initAdapter()
    }

    private fun initData() {
        thunderListViewModel.getLightCategoryList(ThunderListActivity.CATEGORY_GO)
    }
}
