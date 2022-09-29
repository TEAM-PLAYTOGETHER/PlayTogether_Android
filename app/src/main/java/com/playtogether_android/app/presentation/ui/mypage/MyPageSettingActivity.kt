package com.playtogether_android.app.presentation.ui.mypage

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.core.app.NotificationManagerCompat
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityMyPageSettingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.login.LoginActivity
import com.playtogether_android.app.presentation.ui.main.WebViewActivity
import com.playtogether_android.app.presentation.ui.onboarding.OnBoardingIntroduceActivity
import com.playtogether_android.app.presentation.ui.userInfo.MyInfoFragment
import com.playtogether_android.data.singleton.PlayTogetherRepository

class MyPageSettingActivity :
    BaseActivity<ActivityMyPageSettingBinding>(R.layout.activity_my_page_setting) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMovePage()
    }

    private fun initIntent(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }


    private fun initMovePage() {
        binding.ivSettingBack.setOnClickListener {
            finish()
        }
        //버전정보
        binding.tvSettingVersion.setOnClickListener {
            initIntent("https://cheddar-liquid-051.notion.site/14fc6c632471488486e7e76bc161069e")
        }

        //이용가이드
        binding.tvSettingGuide.setOnClickListener {
            initIntent("https://cheddar-liquid-051.notion.site/1a22db662cb5416caaf6d08b58e98b1a")
        }

        //정책 및 약관
        binding.tvSettingPolicy.setOnClickListener {
            initIntent("https://cheddar-liquid-051.notion.site/fc9dae6b1917453bbb1a39c4be4e4297")
        }

        //개발자 정보
        binding.tvSettingDeveloper.setOnClickListener {
            initIntent("https://cheddar-liquid-051.notion.site/9b109514a74349f4988c9b7f72fe4e47")
        }

        //계정 관리
        binding.tvSettingAccont.setOnClickListener {
            startActivity(Intent(this, ManageAccountActivity::class.java))
        }

        //알림 설정
        binding.tvSettingAlarm.setOnClickListener {
            checkAlarm()
        }

        //로그아웃
        binding.tvSettingLogout.setOnClickListener {
            with(PlayTogetherRepository) {
                //todo 로그아웃한 경우 남아있는 Preferenece를 제거해야 소셜로그인 번갈아가면서 사용할 때 좋을거같아서 수정합니다.
                crewId = -1
                crewName = ""
                userLogin = false
                googleUserToken = ""
                kakaoUserToken = ""
                userToken = ""
            }

            Intent(this, LoginActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(this)
            }
        }
    }


    //버전에 따라 설정창 이동
    private fun checkAlarm() {
        val intent = Intent()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, this.packageName)
            }

            else -> {
                intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
                intent.putExtra("app_package", this.packageName)
                intent.putExtra("app_uid", this.applicationInfo?.uid)
                NotificationManagerCompat.from(this).areNotificationsEnabled()
            }
        }
        this.startActivity(intent)
    }

}