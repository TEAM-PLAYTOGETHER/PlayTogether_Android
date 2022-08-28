package com.playtogether_android.app.presentation.ui.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityManageAccountBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class ManageAccountActivity : BaseActivity<ActivityManageAccountBinding>(R.layout.activity_manage_account) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickListener()
    }

    private fun clickListener() {
        binding.tvQuit.setOnClickListener {
            startActivity(Intent(this, MyPageQuitActivity::class.java))
        }

        binding.ivSettingBack.setOnClickListener {
            startActivity(Intent(this, MyPageSettingActivity::class.java))
            finish()
        }
    }
}