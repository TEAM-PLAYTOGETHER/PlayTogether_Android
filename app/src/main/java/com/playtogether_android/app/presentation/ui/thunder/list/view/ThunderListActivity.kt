package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderListBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import org.koin.core.instance.getArguments

class ThunderListActivity :
    BaseActivity<ActivityThunderListBinding>(R.layout.activity_thunder_list) {

    private lateinit var thunderCategoryListAdapter: ThunderCategoryListAdapter
    private val thunderListViewModel: ThunderListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.fabThunderlist.layoutParams.apply {
            width = resources.getDimension(R.dimen.fab_size).toInt()
            height = resources.getDimension(R.dimen.fab_size).toInt()
        }
        initData()
        initAdapter()
        setClickListener()
    }

    private fun initData() {
        val category = intent.getStringExtra("category") ?: CATEGORY_EAT
        thunderListViewModel.getLightList(category, SORT_CREATE_AT)
    }

    private fun initAdapter() {
        thunderCategoryListAdapter = ThunderCategoryListAdapter()

        thunderListViewModel.categoryList.observe(this) {
            thunderCategoryListAdapter.submitList(it)
        }

        with(binding.rvThunderlistContainer) {
            layoutManager = LinearLayoutManager(this@ThunderListActivity)
            adapter = thunderCategoryListAdapter
        }
    }

    private fun setClickListener() {
        sortListClickListener()
        floatButtonClickListener()
    }

    private fun sortListClickListener() {

    }

    private fun floatButtonClickListener() {

    }

    companion object {
        const val CATEGORY_EAT = "먹을래"
        const val CATEGORY_DO = "할래"
        const val CATEGORY_GO = "갈래"
        const val SORT_PEOPLE_CNT = "peopleCnt"
        const val SORT_CREATE_AT = "createdAt"
    }
}