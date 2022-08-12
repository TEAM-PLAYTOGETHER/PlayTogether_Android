package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderListBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.createThunder.CreateThunderActivity
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_DO
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_EAT
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel.Companion.CATEGORY_GO
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
//     binding.thunderViewModel = thunderListViewModel
        binding.lifecycleOwner = this
        initData()
        initView()
    }

    private fun setClickListener() {
//        sortListClickListener()
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
        initFragment()
        setClickListener()
        setCategory()
        setUpdateCategory()
    }

    private fun setUpdateCategory() {
        binding.vpThunderlistContainer.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvThunderlistCategoryTitle.text = categoryTitleList[position]
                binding.tvThunderlistToolTitle.text = categoryTitleList[position]
            }
        })
    }

    private fun setCategory() {
        val category = intent.getStringExtra("category") ?: CATEGORY_EAT
        val index = categoryTitleList.indexOf(category)

        with(binding) {
            tvThunderlistCategoryTitle.text = category
            tvThunderlistToolTitle.text = category
            vpThunderlistContainer.setCurrentItem(index, false)
        }

    }

    private fun initFragment() {
        thunderCategoryListAdapter = ThunderCategoryListAdapter(this)
        thunderCategoryListAdapter.fragmentList.addAll(fragmentList)
        binding.vpThunderlistContainer.adapter = thunderCategoryListAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        binding.listViewModel = thunderListViewModel
//        val category = intent.getStringExtra("category") ?: CATEGORY_EAT
//        thunderListViewModel.getLightCategoryList(category)
        with(thunderListViewModel) {
            getLightCategoryList(CATEGORY_EAT)
            getLightCategoryList(CATEGORY_GO)
            getLightCategoryList(CATEGORY_DO)
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
        const val SORT_PEOPLE_CNT = "peopleCnt"
        const val SORT_CREATE_AT = "createdAt"
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

//    private fun sortListClickListener() {
//        binding.llThunderAlignContainer.setOnClickListener {
//            val category = thunderListViewModel.category.value
//            val bottomSheetDialog = SortDialog(thunderListViewModel, category)
//            bottomSheetDialog.show(supportFragmentManager, "init bottom_sheet")
//        }
//    }
}