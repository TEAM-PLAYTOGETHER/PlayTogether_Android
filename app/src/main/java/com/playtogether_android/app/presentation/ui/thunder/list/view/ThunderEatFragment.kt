package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentThunderEatBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListItemAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_EAT
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.MORE
import com.playtogether_android.app.util.SpaceItemDecoration
import timber.log.Timber

class ThunderEatFragment :
    BaseFragment<FragmentThunderEatBinding>(R.layout.fragment_thunder_eat) {
    private lateinit var listAdapter: ThunderCategoryListItemAdapter
    private val thunderListViewModel: ThunderListViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listViewModel = thunderListViewModel
        binding.lifecycleOwner = this
        initAdapter()
        observingList()
    }

    //먹/갈/할 같이 사용할 메서드
    private fun initAdapter() {
        listAdapter = ThunderCategoryListItemAdapter()
        with(binding.rvThundereatContainer) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpaceItemDecoration(0, 10, 0, 0))
            adapter = listAdapter
            itemAnimator = null
            addOnScrollListener(MyScrollListener())
        }
    }

    inner class MyScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!binding.rvThundereatContainer.canScrollVertically(1)) {
                if (thunderListViewModel.isLastPage) return
                Timber.e("asdf : 끝에 도달했다")
                thunderListViewModel.getLightCategoryList(MORE, CATEGORY_EAT)
            }
        }
    }

    private fun observingList() {
        thunderListViewModel.eatingItemList.observe(viewLifecycleOwner) {
            Timber.e("life cycle : submitList")
            listAdapter.submitList(it)
        }
    }
}