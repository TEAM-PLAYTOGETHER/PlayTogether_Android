package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderListBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.ThunderDetailActivity
import com.playtogether_android.app.presentation.ui.search.SearchActivity
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_DO
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_EAT
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_GO
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.DEFAULT_SORT
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.FIRST
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.LATER
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.PREV
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.SCPCNT
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ThunderListActivity :
    BaseActivity<ActivityThunderListBinding>(R.layout.activity_thunder_list) {
    private lateinit var thunderDetailLauncher: ActivityResultLauncher<Intent>
    private lateinit var thunderCategoryListAdapter: ThunderCategoryListAdapter
    private val thunderListViewModel: ThunderListViewModel by viewModels()
    val categoryTitleList = listOf(CATEGORY_EAT, CATEGORY_GO, CATEGORY_DO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.listViewModel = thunderListViewModel
        binding.thunderListActivity = this
        binding.lifecycleOwner = this
        initAdapter()
        setCategory()
        updateCategory()
        initTabLayout()
        initList()
        observingSort()
        initLauncher()

        clickBackButton()
        clickSearchButton()
        clickNextButton()
        clickPrevButton()
    }

    private fun clickSearchButton() {
        binding.ivThunderlistSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            val category: String? = when (thunderListViewModel.pageOrder.value) {
                0 -> CATEGORY_EAT
                1 -> CATEGORY_GO
                2 -> CATEGORY_DO
                else -> null
            }
            intent.putExtra("category", category)
            startActivity(intent)
        }
    }

    private fun clickBackButton() {
        binding.ivThunderlistBack.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {
        val fragmentList =
            listOf(ThunderEatFragment(), ThunderGoFragment(), ThunderDoFragment())
        thunderCategoryListAdapter = ThunderCategoryListAdapter(this)
        thunderCategoryListAdapter.fragmentList.addAll(fragmentList)
        binding.vpThunderlistContainer.adapter = thunderCategoryListAdapter
    }

    private fun clickNextButton() {
        binding.ivThunderlistNext.setOnClickListener {
            val index = thunderListViewModel.pageOrder.value ?: 0
            if (index < 2) {
                thunderListViewModel.pageOrder.value = index + 1
                binding.vpThunderlistContainer.setCurrentItem(index + 1, true)
            }
        }
    }

    private fun clickPrevButton() {
        binding.ivThunderlistPrevious.setOnClickListener {
            val index = thunderListViewModel.pageOrder.value ?: 0
            if (index > 0) {
                thunderListViewModel.pageOrder.value = index - 1
                binding.vpThunderlistContainer.setCurrentItem(index - 1, true)
            }
        }
    }

    private fun updateCategory() {
        binding.vpThunderlistContainer.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                thunderListViewModel.pageOrder.value = position
            }
        })
    }

    private fun setCategory() {
        val category = intent.getStringExtra("category") ?: CATEGORY_EAT
        val index = categoryTitleList.indexOf(category)
        thunderListViewModel.pageOrder.value = index
        Timber.e("category : $category , index : $index")
        binding.vpThunderlistContainer.setCurrentItem(index, false)
    }

    private fun initTabLayout() {
        binding.tlThunderlistTabContainer.apply {
            layoutParams.height = resources.getDimension(R.dimen.tab_sort_height).toInt()
            tabRippleColor = null
            clipToPadding = true
            addOnTabSelectedListener(SortTabListener())
        }
    }

    inner class SortTabListener : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            when (tab?.position) {
                0 -> thunderListViewModel.sort.value = DEFAULT_SORT
                1 -> thunderListViewModel.sort.value = SCPCNT
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabReselected(tab: TabLayout.Tab?) {}
    }

    private fun initList() {
        with(thunderListViewModel) {
            getLightCategoryList(FIRST, CATEGORY_EAT)
            getLightCategoryList(FIRST, CATEGORY_DO)
            getLightCategoryList(FIRST, CATEGORY_GO)
        }
    }

    private fun observingSort() {
        thunderListViewModel.sort.observe(this) {
            Timber.e("asdf : FIRST2 호출됨 ${it}")
            thunderListViewModel.getLightCategoryList(FIRST, thunderListViewModel.currentCategory())
            thunderListViewModel.isLastPage = false
        }
    }

    fun clickThunderListItem(thunderId: Int) {
        thunderListViewModel.getScrapValue(thunderId, PREV)
        thunderListViewModel.adapterThunderId = thunderId
        val intent = Intent(this, ThunderDetailActivity::class.java)
        intent.putExtra("thunderId", thunderId)
        thunderDetailLauncher.launch(intent)
    }

    private fun initLauncher() {
        thunderDetailLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val thunderId = thunderListViewModel.adapterThunderId ?: -1
                thunderListViewModel.getScrapValue(thunderId, LATER)
            }
    }
}