package com.playtogether_android.app.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOpenCrewEndOnBoardingBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class OpenCrewEndOnBoardingActivity :
    BaseActivity<ActivityOpenCrewEndOnBoardingBinding>(R.layout.activity_open_crew_end_on_boarding) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSetting()
    }

    private fun initSetting() {
        val crewName = intent.getStringExtra("crewName")
        val crewIntroduce = intent.getStringExtra("crewIntro")
        val crewCode = intent.getStringExtra("crewCode")
        val userName = intent.getStringExtra("userName")

        binding.tvOpenEndOnboardingName.setText(userName)
        binding.tvOpenEndOnboardingCrew.setText(crewName)
        binding.tvOpenEndOnboardingCrewName.setText(crewName)
        binding.tvOpenEndOnboardingIntroAnswer.setText(crewIntroduce)
        binding.tvOpenEndOnboardingCodeAnswer.setText(crewCode)

        binding.tvOpenOnboardingNext.setOnClickListener {
            val intent = Intent(this, OnBoardingIntroduceActivity::class.java)
            intent.putExtra("crewName", crewName)
            startActivity(intent)
            finish()
        }

        binding.tvOpenEndOnboardingShare.setOnClickListener {

            val text = "[Play Together!]\n" +
                    "\n" +
                    "$userName 님이 '$crewName'에 당신을 초대했습니다!\n" +
                    "지금 바로 아래 초대 코드를 입력하고 '$crewName' 회원들이 개설한 번개에 참여해보세요!\n" +
                    "\n" +
                    "초대코드 : $crewCode\n" +
                    "\n" +
                    "▶ 아직 플레이투게더를 설치하지 않으셨나요? \n" +
                    "안드로이드 플레이스토어 \n" +
                    "playstore.com/playtogether\n" +
                    "\n" +
                    "iOS 앱스토어\n" +
                    "appstore.com/playtogether"

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}