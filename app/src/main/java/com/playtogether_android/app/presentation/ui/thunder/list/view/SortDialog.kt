package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.os.Bundle
import android.view.View
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.DialogSortBinding
import com.playtogether_android.app.presentation.base.BaseBottomDialogFragment
import com.playtogether_android.app.presentation.ui.thunder.list.viewmodel.ThunderListViewModel

class SortDialog(
    private val thunderListViewModel: ThunderListViewModel,
    private val category: String?
) :
    BaseBottomDialogFragment<DialogSortBinding>(R.layout.dialog_sort) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        binding.thunderListViewModel = thunderListViewModel
        initData()
        setClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //todo Result ok callback 넣어야함
    }

    private fun initData() {
        val sortType = thunderListViewModel.sortType.value
        if (sortType == CREATE_AT) {
            binding.tvSortCreateAt.setTextAppearance(R.style.sort_select_item)
        } else {
            binding.tvSortPeopleCnt.setTextAppearance(R.style.sort_select_item)
        }
    }

    private fun setClickListener() {
        binding.ivSortCancel.setOnClickListener {
            dismiss()
        }
        binding.tvSortCreateAt.setOnClickListener {
            thunderListViewModel.getLightCategoryList(category!!, CREATE_AT)
            thunderListViewModel.sortType.observe(this) {
                if (it == CREATE_AT) {
                    dismiss()
                }
            }
        }
        binding.tvSortPeopleCnt.setOnClickListener {
            thunderListViewModel.getLightCategoryList(category!!, PEOPLE_CNT)
            thunderListViewModel.sortType.observe(this) {
                if (it == PEOPLE_CNT) {
                    dismiss()
                }
            }
        }
    }

    companion object {
        const val CREATE_AT = "createdAt"
        const val PEOPLE_CNT = "peopleCnt"
    }

}
