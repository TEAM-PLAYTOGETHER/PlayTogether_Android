package com.playtogether_android.app.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentHomeBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeVieModel: HomeViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        //refreshView()
        initAdapter()
    }

    private fun initAdapter() {

    }

    private fun initData() {

    }

    /*private fun refreshView() {
        with(binding) {
            lsrlHomeContainer.setOnRefreshListener {
                //해당 부분에 애니메이션 넣는건가? ex) 배경 0.5초 검은색
                lsrlHomeContainer.isRefreshing = false
            }
        }
    }*/

}
