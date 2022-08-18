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
        initIntent()

    }

    private fun initIntent() {
        binding.tvSettingGuide.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", "www.naver.com")
            startActivity(intent)
        }
    }
}