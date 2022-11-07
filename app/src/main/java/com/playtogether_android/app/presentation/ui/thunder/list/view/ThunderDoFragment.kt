package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentThunderDoBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.search.SearchViewModel.Companion.DO
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListItemAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_DO
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.DEFAULT_SORT
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.FIRST
import com.playtogether_android.app.util.SpaceItemDecoration

class ThunderDoFragment :
    BaseFragment<FragmentThunderDoBinding>(R.layout.fragment_thunder_do) {
    private lateinit var listAdapter: ThunderCategoryListItemAdapter
    private val thunderListViewModel: ThunderListViewModel by activityViewModels()
    private var isFirstPage = true
    private var sort = DEFAULT_SORT

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listViewModel = thunderListViewModel
        binding.lifecycleOwner = this
        initAdapter()
        observingList()
        observingScrap()
    }

    override fun onResume() {
        super.onResume()
        checkSort()
    }

    private fun checkSort() {
        if (sort != thunderListViewModel.sort.value) {
            thunderListViewModel.getLightCategoryList(FIRST, DO)
        }
    }

    private fun initAdapter() {
        listAdapter = ThunderCategoryListItemAdapter { thunderId, position ->
            adapterLamda(thunderId, position)
        }
        with(binding.rvThunderdoContainer) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpaceItemDecoration(0, 10, 0, 0))
            adapter = listAdapter
            itemAnimator = null
            addOnScrollListener(MyScrollListener())
        }
    }

    private fun adapterLamda(thunderId: Int, position: Int) {
        (activity as ThunderListActivity).clickThunderListItem(thunderId)
        thunderListViewModel.adapterPosition = position
    }

    inner class MyScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!binding.rvThunderdoContainer.canScrollVertically(1)) {
                if (thunderListViewModel.isLastPage) return
                thunderListViewModel.getLightCategoryList(ThunderListViewModel.MORE, CATEGORY_DO)
            }
        }
    }

    private fun observingList() {
        thunderListViewModel.doingItemList.observe(viewLifecycleOwner) {
            when (isFirstPage) {
                true -> listAdapter.initList(it)
                false -> listAdapter.addList(it)
            }
            sort = thunderListViewModel.sort.value ?: DEFAULT_SORT
        }
    }

    private fun changeScrapCount() {
        with(thunderListViewModel) {
            if ((prevScrapState ?: return) and !((laterScrapState.value) ?: return)) {
                listAdapter.updateScrapCount(
                    adapterPosition ?: return,
                    ThunderListViewModel.SCRAP_MINUS
                )
            } else if (!(prevScrapState ?: return) and (laterScrapState.value ?: return)) {
                listAdapter.updateScrapCount(
                    adapterPosition ?: return,
                    ThunderListViewModel.SCRAP_PLUS
                )
            }
        }
    }

    private fun observingScrap() {
        thunderListViewModel.laterScrapState.observe(viewLifecycleOwner) {
            changeScrapCount()
        }
    }
}
