package com.playtogether_android.app.presentation.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderAppliedBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.playtogether_android.domain.model.home.ThunderJoinEndData
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThunderAppliedActivity : BaseActivity<ActivityThunderAppliedBinding>(R.layout.activity_thunder_applied) {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
    }

    private fun initData() {
        val thunderId = intent.getIntExtra("thunderId", -1)
        homeViewModel.getThunderJoinEnd(thunderId)
        homeViewModel.endThunder.observe(this){
            binding.thunder = it
            //binding.thunder?.title = binding.tvThunderdetailTitle.toString()
            //Log.d("test", ""+binding.thunder?.title)
        }
    }
}