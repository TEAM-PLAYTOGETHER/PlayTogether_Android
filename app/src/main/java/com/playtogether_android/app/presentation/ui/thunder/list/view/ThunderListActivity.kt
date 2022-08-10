package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderListBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.createThunder.CreateThunderActivity
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListItemAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListAdapter
import com.playtogether_android.app.util.SpaceItemDecorationVertical

@AndroidEntryPoint
class ThunderListActivity :
    BaseActivity<ActivityThunderListBinding>(R.layout.activity_thunder_list) {
    private lateinit var thunderCategoryListItemAdapter: ThunderCategoryListItemAdapter
    private lateinit var thunderCategoryListAdapter: ThunderCategoryListAdapter
    private val thunderListViewModel: ThunderListViewModel by viewModels()
    private val categoryTitleList = listOf(CATEGORY_EAT, CATEGORY_GO, CATEGORY_DO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.thunderViewModel = thunderListViewModel
        initView()
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    private fun setClickListener() {
        sortListClickListener()
        floatButtonClickListener()
        searchButtonClickListener()
//        setPreCategoryClickListener()
//        setAfterCategoryClickListener()
        setPreButtonListener()
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

        initData()
//        initAdapter()
        initFragment()
        setClickListener()
    }


//        private fun initAdapter() {
//            thunderCategoryListItemAdapter = ThunderCategoryListItemAdapter()
//
//            with(thunderListViewModel) {
//                categoryItemList.observe(this@ThunderListActivity) { it ->
//                    thunderCategoryListItemAdapter.submitList(it)
//                }
//            }
//
//            with(binding.rvThunderlistContainer) {
//                layoutManager = LinearLayoutManager(this@ThunderListActivity)
//                addItemDecoration(SpaceItemDecorationVertical())
//                adapter = thunderCategoryListItemAdapter
//            }
//        }
    private fun initFragment() {
        val fragmentList = listOf(ThunderEatFragment(), ThunderGoFragment(), ThunderDoFragment())
        thunderCategoryListAdapter = ThunderCategoryListAdapter(this)
        thunderCategoryListAdapter.fragmentList.addAll(fragmentList)
        binding.vpThunderlistContainer.adapter = thunderCategoryListAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        val category = intent.getStringExtra("category")
        val variableCategory = thunderListViewModel.category.value ?: category
        thunderListViewModel.getLightCategoryList(variableCategory!!)
        thunderListViewModel.category.observe(this) {
            binding.tvThunderlistToolTitle.text = it
            binding.tvThunderlistCategoryTitle.text = it + getString(R.string.thunder_list_question)
        }

        thunderListViewModel.sortType.observe(this) {
            binding.tvThunderlistSortType.text = thunderListViewModel.setSortType(it)
        }
    }

//    }

    private fun setPreButtonListener() {
        binding.ivThunderlistBack.setOnClickListener {
            finish()
        }
    }

//    private fun setPreCategoryClickListener() {
//        var category = ""
//        thunderListViewModel.category.observe(this) {
//            category = it
//        }
//        if (category == CATEGORY_EAT) {
//            binding.ivThunderlistPrevious.setImageResource(R.drawable.icn_before_false)
//        } else {
//            binding.ivThunderlistPrevious.setImageResource(R.drawable.icn_before_true)
//            with(thunderListViewModel) {
//                binding.ivThunderlistPrevious.setOnClickListener {
//                    when (categoryTitleList.indexOf(category)) {
//                        1 -> {
//                            getLightCategoryList(CATEGORY_EAT)
//                        }
//                        2 -> {
//                            getLightCategoryList(CATEGORY_GO)
//                        }
//                    }
//                }
//            }
//        }
//    }

//    private fun setAfterCategoryClickListener() {
//        var category = ""
//        thunderListViewModel.category.observe(this) {
//            category = it
//        }
//        with(binding.ivThunderlistNext) {
//            if (category.equals(CATEGORY_DO)) {
//                setImageResource(R.drawable.icn_next_false)
//            } else {
//                setImageResource(R.drawable.icn_next_true)
//                with(thunderListViewModel) {
//                    setOnClickListener {
//                        when (categoryTitleList.indexOf(category)) {
//                            0 -> {
//                                getLightCategoryList(CATEGORY_GO)
//                            }
//                            1 -> {
//                                getLightCategoryList(CATEGORY_DO)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

    private fun sortListClickListener() {
        binding.llThunderAlignContainer.setOnClickListener {
            val category = thunderListViewModel.category.value
            val bottomSheetDialog = SortDialog(thunderListViewModel, category)
            bottomSheetDialog.show(supportFragmentManager, "init bottom_sheet")
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

    companion object {
        const val CATEGORY_EAT = "먹을래"
        const val CATEGORY_GO = "갈래"
        const val CATEGORY_DO = "할래"
        const val SORT_PEOPLE_CNT = "peopleCnt"
        const val SORT_CREATE_AT = "createdAt"
    }
}