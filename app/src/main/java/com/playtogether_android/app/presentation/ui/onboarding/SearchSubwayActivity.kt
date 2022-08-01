package com.playtogether_android.app.presentation.ui.onboarding

import android.os.Bundle
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivitySearchSubwayBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.onboarding.adapter.OnboardingReDownAdapter
import com.playtogether_android.app.presentation.ui.onboarding.adapter.OnboardingSubwayAdapter
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.domain.model.onboarding.SubwayListData
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchSubwayActivity :
    BaseActivity<ActivitySearchSubwayBinding>(R.layout.activity_search_subway) {

    private val onBoardingViewModel: OnBoardingViewModel by viewModels()
    private lateinit var onboardingSubwayAdapter: OnboardingSubwayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subwayNetwork()
    }


    private fun subwayNetwork() {
        onBoardingViewModel.getSubwayList()
        onboardingSubwayAdapter = OnboardingSubwayAdapter()
        onBoardingViewModel.subwayList.observe(this) {
            Timber.d("SubwayList : $it")
            onboardingSubwayAdapter.setSubwayList((it) as MutableList<SubwayListData>)
        }
    }
}