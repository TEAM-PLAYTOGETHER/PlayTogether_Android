package com.playtogether_android.app.presentation.ui.thunder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentThunderBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.search.SearchActivity
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThunderFragment : BaseFragment<FragmentThunderBinding>(R.layout.fragment_thunder) {

    private lateinit var thunderTabViewPagerAdapter: ThunderTabViewPagerAdapter
    private val thunderViewModel: ThunderViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initTabLayout()
//        initData()
        clickSearch()
    }

    private fun initData() {

    }

    private fun clickSearch(){
        binding.ivThundertapSearch.setOnClickListener{
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initAdapter() {
        val fragmentList = listOf(TabApplyFragment(), TabOpenFragment(), TabLikeFragment())
        thunderTabViewPagerAdapter = ThunderTabViewPagerAdapter(requireActivity())
        thunderTabViewPagerAdapter.fragments.addAll(fragmentList)
        binding.vpThunderTab.adapter = thunderTabViewPagerAdapter
    }

    private fun initTabLayout() {
        // TODO: 탭 글자크기, font 적용 
        val tabLabel = listOf("신청한", "오픈한", "찜한")

        TabLayoutMediator(binding.tlThunderTab, binding.vpThunderTab) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }

}