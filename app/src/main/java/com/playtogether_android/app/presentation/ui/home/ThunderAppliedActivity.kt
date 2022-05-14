package com.playtogether_android.app.presentation.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderAppliedBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.adapter.HomeListAdapter
import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.playtogether_android.app.presentation.ui.thunder.ApplicantListAdapter
import com.playtogether_android.domain.model.home.ThunderJoinEndData
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThunderAppliedActivity : BaseActivity<ActivityThunderAppliedBinding>(R.layout.activity_thunder_applied) {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var homeListAdapter: HomeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        initbackBtn()
        initAdapter()
    }

    private fun initData() {
        val thunderId = intent.getIntExtra("thunderId", -1)
        homeViewModel.getThunderJoinEnd(thunderId)
        homeViewModel.endThunder.observe(this){
            binding.thunder = it
        }

        homeViewModel.organizerInfo.observe(this) {
            binding.organizer = it
        }
    }

    private fun initbackBtn() {
        binding.ivThunderdetailBack.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {
        homeListAdapter = HomeListAdapter()
        binding.rvThunderdetail.adapter = homeListAdapter

        homeViewModel.memberList.observe(this) {
            homeListAdapter.applicantList.addAll(it)
            homeListAdapter.notifyDataSetChanged()
        }
    }

}