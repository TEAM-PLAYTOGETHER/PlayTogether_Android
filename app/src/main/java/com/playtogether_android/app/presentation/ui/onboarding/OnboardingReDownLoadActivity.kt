package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOnboardingReDownLoadBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.login.LoginActivity
import com.playtogether_android.app.presentation.ui.main.MainActivity
import com.playtogether_android.app.presentation.ui.onboarding.adapter.OnboardingReDownAdapter
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.data.singleton.PlayTogetherRepository
//import com.playtogether_android.app.presentation.ui.sign.SignInActivity
import com.playtogether_android.domain.model.onboarding.CrewListData
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class OnboardingReDownLoadActivity : BaseActivity<ActivityOnboardingReDownLoadBinding>(R.layout.activity_onboarding_re_down_load) {


    private lateinit var onboardingReDownAdapter: OnboardingReDownAdapter
    private val onBoardingViewModel : OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterBtnListener()
        backBtnListener()
    }

    override fun onResume() {
        super.onResume()
        initList()
    }

    //리스트 세팅
    private fun initList() {
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
                    PlayTogetherRepository.crewId = crewId
                    binding.tvIntroOnboardingNext.isSelected = true
                }

            }
        )
    }

    //입장하기버튼 클릭 리스너
    private fun enterBtnListener() {
        binding.tvIntroOnboardingNext.setOnClickListener {
            if(binding.tvIntroOnboardingNext.isSelected) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    //뒤로가기버튼 클릭 리스너
    private fun backBtnListener() {
        binding.ivIntroOnboardingBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}