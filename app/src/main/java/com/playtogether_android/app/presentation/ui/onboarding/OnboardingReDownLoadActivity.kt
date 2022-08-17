package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOnboardingReDownLoadBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.main.MainActivity
import com.playtogether_android.app.presentation.ui.onboarding.adapter.OnboardingReDownAdapter
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.domain.model.onboarding.CrewListData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import timber.log.Timber


@AndroidEntryPoint
class OnboardingReDownLoadActivity : BaseActivity<ActivityOnboardingReDownLoadBinding>(R.layout.activity_onboarding_re_down_load) {


    private lateinit var onboardingReDownAdapter: OnboardingReDownAdapter
    private val onBoardingViewModel : OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterBtnListener()
    }

    override fun onResume() {
        super.onResume()
        initList()
    }

    private fun initList() {
        Timber.d("TEST CODE1")
        onBoardingViewModel.getCrewList()

        onboardingReDownAdapter = OnboardingReDownAdapter()
        binding.rvLikeLinear.adapter = onboardingReDownAdapter
        onBoardingViewModel.getCrewList.observe(this) {
            onboardingReDownAdapter.setCrewList((it.data.crewList) as MutableList<CrewListData.Data.CrewList>)
        }

        onboardingReDownAdapter.setItemClickListener(
            object : OnboardingReDownAdapter.ItemClickListener{
                override fun onClick(view: View, position: Int) {
                    val crewId = onboardingReDownAdapter.dataList[position].id
                    Timber.d("CrewId : $crewId")
                    binding.tvIntroOnboardingNext.isSelected = true
                }

            }
        )


    }

    private fun enterBtnListener() {
        binding.tvIntroOnboardingNext.setOnClickListener {
            if(binding.tvIntroOnboardingNext.isSelected) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}