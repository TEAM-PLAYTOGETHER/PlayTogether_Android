package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentThunderGoBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity
import com.playtogether_android.app.presentation.ui.search.SearchViewModel.Companion.GO
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListItemAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderGoViewModel
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_GO
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.DEFAULT_SORT
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.FIRST
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.MORE
import com.playtogether_android.app.util.SpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ThunderGoFragment :
    BaseFragment<FragmentThunderGoBinding>(R.layout.fragment_thunder_go) {
    lateinit var thunderDetailLauncher: ActivityResultLauncher<Intent>
    private lateinit var listAdapter: ThunderCategoryListItemAdapter
    private val thunderGoViewModel: ThunderGoViewModel by viewModels()
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
        initLauncher()
    }

    override fun onResume() {
        super.onResume()
        checkSort()
    }

    private fun checkSort() {
        if (sort != thunderListViewModel.sort.value) {
            thunderListViewModel.getLightCategoryList(FIRST, GO)
        }
    }

    private fun initAdapter() {
        listAdapter = ThunderCategoryListItemAdapter { thunderId, position ->
            adapterLamda(thunderId, position)
        }
        with(binding.rvThundergoContainer) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpaceItemDecoration(0, 10, 0, 0))
            adapter = listAdapter
            itemAnimator = null
            addOnScrollListener(MyScrollListener())
        }
    }

    private fun adapterLamda(thunderId: Int, position: Int) {
        clickThunderListItem(thunderId)
        thunderGoViewModel.adapterPosition = position
        Timber.e("asdf adapterPosition : ${thunderGoViewModel.adapterPosition}")
    }

    private fun clickThunderListItem(thunderId: Int) {
        thunderGoViewModel.getScrapValue(thunderId, ThunderListViewModel.PREV)
        thunderGoViewModel.adapterThunderId = thunderId
        val intent = Intent(requireContext(), ThunderDetailActivity::class.java)
        intent.putExtra("thunderId", thunderId)
        thunderDetailLauncher.launch(intent)
    }

    inner class MyScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!binding.rvThundergoContainer.canScrollVertically(1)) {
                if (thunderListViewModel.isLastPage) return
                thunderListViewModel.getLightCategoryList(MORE, CATEGORY_GO)
            }
        }
    }

    private fun observingList() {
        thunderListViewModel.goingItemList.observe(viewLifecycleOwner) {
            when (isFirstPage) {
                true -> listAdapter.initList(it)
                false -> listAdapter.addList(it)
            }
            sort = thunderListViewModel.sort.value ?: DEFAULT_SORT
        }
    }

    private fun changeScrapCount() {
        with(thunderGoViewModel) {
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

    private fun initLauncher() {
        thunderDetailLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val thunderId = thunderGoViewModel.adapterThunderId ?: -1
                thunderGoViewModel.getScrapValue(thunderId, ThunderListViewModel.LATER)
            }
    }
}
