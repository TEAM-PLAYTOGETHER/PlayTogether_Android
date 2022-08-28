package com.playtogether_android.app.presentation.ui.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityMyPageSettingBinding
import com.playtogether_android.app.databinding.ActivityWebViewBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.main.WebViewActivity

class MyPageSettingActivity : BaseActivity<ActivityMyPageSettingBinding>(R.layout.activity_my_page_setting) {
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

        //신고하기
        binding.tvSettingWarn.setOnClickListener {
            initIntent("https://forms.gle/7deZ5JgtVqrbTifG8")
        }

        //계정 관리
        binding.tvSettingAccont.setOnClickListener {
            startActivity(Intent(this, ManageAccountActivity::class.java))
        }
    }
}