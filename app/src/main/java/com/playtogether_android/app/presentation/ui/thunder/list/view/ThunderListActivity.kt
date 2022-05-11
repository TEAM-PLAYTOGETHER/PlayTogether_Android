package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderListBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.createThunder.CreateThunderActivity
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel
import com.playtogether_android.app.util.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ThunderListActivity :
    BaseActivity<ActivityThunderListBinding>(R.layout.activity_thunder_list) {

    private lateinit var thunderCategoryListAdapter: ThunderCategoryListAdapter
    private val thunderListViewModel: ThunderListViewModel by viewModel()
    private val categoryTitleList = listOf(CATEGORY_EAT, CATEGORY_GO, CATEGORY_DO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        initData()
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

    @SuppressLint("SetTextI18n")
    private fun initData() {
        thunderListViewModel.getLightCategoryList()
        thunderListViewModel.category.observe(this) {
            binding.tvThunderlistToolTitle.text = it
            binding.tvThunderlistCategoryTitle.text = it + getString(R.string.thunder_list_question)
        }
    }

    private fun initAdapter() {
        thunderCategoryListAdapter = ThunderCategoryListAdapter()

        with(thunderListViewModel) {
            categoryItemList.observe(this@ThunderListActivity) { it ->
                thunderCategoryListAdapter.submitList(it)
            }
        }

        with(binding.rvThunderlistContainer) {
            layoutManager = LinearLayoutManager(this@ThunderListActivity)
            adapter = thunderCategoryListAdapter
        }
    }

    private fun setClickListener() {
        sortListClickListener()
        floatButtonClickListener()
        setPreCategoryClickListener()
        setAfterCategoryClickListener()
    }

    private fun setPreCategoryClickListener() {
        var category = ""
        thunderListViewModel.category.observe(this) {
            category = it
        }
        if (category == CATEGORY_EAT) {
            binding.ivThunderlistPrevious.setImageResource(R.drawable.icn_before_false)
        } else {
            binding.ivThunderlistPrevious.setImageResource(R.drawable.icn_before_true)
            with(thunderListViewModel) {
                binding.ivThunderlistPrevious.setOnClickListener {
                    when (categoryTitleList.indexOf(category)) {
                        1 -> {
                            getLightCategoryList(CATEGORY_EAT)
                        }
                        2 -> {
                            getLightCategoryList(CATEGORY_GO)
                        }
                    }
                    shortToast("category$category")
                    Timber.d("message : ${category == CATEGORY_EAT}")
                }
            }
        }
    }

    private fun setAfterCategoryClickListener() {
        var category = ""
        thunderListViewModel.category.observe(this) {
            category = it
        }
        with(binding.ivThunderlistNext) {
            if (category.equals(CATEGORY_DO)) {
                setImageResource(R.drawable.icn_next_false)
            } else {
                setImageResource(R.drawable.icn_next_true)
                with(thunderListViewModel) {
                    setOnClickListener {
                        when (categoryTitleList.indexOf(category)) {
                            0 -> {
                                getLightCategoryList(CATEGORY_GO)
                            }
                            1 -> {
                                getLightCategoryList(CATEGORY_DO)
                            }
                        }
                        shortToast("category : $category")
                    }
                }
            }
        }
    }

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
            startActivity(intent)
        }
    }

    companion object {
        const val CATEGORY_EAT = "먹을래"
        const val CATEGORY_GO = "갈래"
        const val CATEGORY_DO = "할래"
        const val SORT_PEOPLE_CNT = "peopleCnt"
        const val SORT_CREATE_AT = "createdAt"
    }
}