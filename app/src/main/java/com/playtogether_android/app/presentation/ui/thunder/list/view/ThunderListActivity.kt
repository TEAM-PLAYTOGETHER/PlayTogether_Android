package com.playtogether_android.app.presentation.ui.thunder.list.view

import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderListBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class ThunderListActivity :
    BaseActivity<ActivityThunderListBinding>(R.layout.activity_thunder_list) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.fabThunderlist.layoutParams.apply {
            height = resources.getDimension(R.dimen.fab_size).toInt()
        }
        initData()
        initAdapter()
        setClickListener()
    }

    private fun initAdapter() {

    }

    private fun initData() {

    }

    private fun setClickListener() {
        sortListClickListener()
        floatButtonClickListener()
    }

    private fun sortListClickListener() {

    }

    private fun floatButtonClickListener() {

    }
}