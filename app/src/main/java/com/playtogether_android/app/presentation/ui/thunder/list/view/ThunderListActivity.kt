package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderListBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.createThunder.CreateThunderActivity
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_DO
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_EAT
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_GO
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.DEFAULT_SORT
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.LIKECNT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThunderListActivity :
    BaseActivity<ActivityThunderListBinding>(R.layout.activity_thunder_list) {
    private lateinit var thunderCategoryListAdapter: ThunderCategoryListAdapter
    private val thunderListViewModel: ThunderListViewModel by viewModels()
    private val fragmentList =
        listOf(ThunderEatFragment(), ThunderGoFragment(), ThunderDoFragment())
    val categoryTitleList = listOf(CATEGORY_EAT, CATEGORY_GO, CATEGORY_DO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.listViewModel = thunderListViewModel
        binding.thunderListActivity = this
        binding.lifecycleOwner = this
        initView()
        initData()
    }

    private fun setClickListener() {
        floatButtonClickListener()
        searchButtonClickListener()
        setBackButtonListener()
        setPreButtonClick()
        setNextButtonClick()
    }

    private fun searchButtonClickListener() {
        binding.ivThunderlistSearch.setOnClickListener {
            //todo coming soon
//        val intent = Intent(this,SearchActivity::class.java)
//        intentActivity(intent)
        }
    }

    private fun initView() {
        binding.fabThunderlist.layoutParams.apply {
            width = resources.getDimension(R.dimen.fab_size).toInt()
            height = resources.getDimension(R.dimen.fab_size).toInt()
        }
        initFragment()
        initTabLayout()
        setClickListener()
        setCategory()
        setUpdateCategory()
    }

    private fun initTabLayout() {
        binding.tlThunderlistTabContainer.apply {
            layoutParams.height = resources.getDimension(R.dimen.tab_sort_height).toInt()
            tabRippleColor = null
            clipToPadding = true
            addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            initData(DEFAULT_SORT)
                        }
                        1 -> {
                            initData(LIKECNT)
                        }
                    }
                    thunderListViewModel.setTabPosition(tab?.position!!)
//                finish()
//                overridePendingTransition(0, 0)
//                val intent = intent
//                startActivity(intent)
//                overridePendingTransition(0, 0)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
//                tab?.setCustomView(R.drawable.bg_tab_unselected)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
//                TODO("Not yet implemented")
                }
            })
        }
    }

    private fun setPreButtonClick() {
        binding.ivThunderlistPrevious.setOnClickListener {
            val index = thunderListViewModel.pageOrder.value ?: 0
            if (index != 0) {
                thunderListViewModel.pageOrder.value = index - 1
                binding.vpThunderlistContainer.setCurrentItem(index - 1, true)
            }
        }
    }

    private fun setNextButtonClick() {
        binding.ivThunderlistNext.setOnClickListener {
            val index = thunderListViewModel.pageOrder.value ?: 2
            if (index != 2) {
                thunderListViewModel.pageOrder.value = index + 1
                binding.vpThunderlistContainer.setCurrentItem(index + 1, true)
            }
        }
    }

    private fun setUpdateCategory() {
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
        with(binding) {
            thunderListViewModel.pageOrder.value = index
            vpThunderlistContainer.setCurrentItem(index, false)
        }
    }

    private fun initFragment() {
        thunderCategoryListAdapter = ThunderCategoryListAdapter(this)
        thunderCategoryListAdapter.fragmentList.addAll(fragmentList)
        binding.vpThunderlistContainer.adapter = thunderCategoryListAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun initData(sortType: String = DEFAULT_SORT) {
        with(thunderListViewModel) {
            getLightCategoryList(CATEGORY_EAT, sortType)
            getLightCategoryList(CATEGORY_GO, sortType)
            getLightCategoryList(CATEGORY_DO, sortType)
        }
    }

    private fun setBackButtonListener() {
        binding.ivThunderlistBack.setOnClickListener {
            finish()
        }
    }

    private fun floatButtonClickListener() {
        binding.fabThunderlist.setOnClickListener {
            val intent = Intent(this, CreateThunderActivity::class.java)
            intentActivity(intent)
        }
    }

    private fun intentActivity(intent: Intent) {
        startActivity(intent)
    }
}