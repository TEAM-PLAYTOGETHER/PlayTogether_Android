package com.playtogether_android.app.presentation.ui.thunder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentTabApplyBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderViewModel
import com.playtogether_android.domain.model.thunder.ThunderTabListData
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Collections.addAll

class TabApplyFragment : BaseFragment<FragmentTabApplyBinding>(R.layout.fragment_tab_apply) {

    private lateinit var thunderListAdapter: ThunderListAdapter

    private val thunderViewModel: ThunderViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initThunderListAdapter()
        getApplyList()
        observeApplyList()

    }


    private fun initThunderListAdapter() {
        thunderListAdapter = ThunderListAdapter()
        with(binding.rvApplyThunderList) {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = thunderListAdapter
        }

        //리스트 클릭시 신청 상세뷰로 이동
        thunderListAdapter!!.itemClick = object : ThunderListAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(context, ApplyThunderDetailActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun getApplyList() {
        thunderViewModel.getApplyList()
    }

    private fun observeApplyList() {
        thunderViewModel.thundertabListData.observe(viewLifecycleOwner) {
            val thunderTabListData = mutableListOf<ThunderTabListData.Data>()
            thunderTabListData.addAll(it.data)
            thunderListAdapter.thunderList = thunderTabListData

            Log.d("connect-test", it.toString())
        }
    }


}
