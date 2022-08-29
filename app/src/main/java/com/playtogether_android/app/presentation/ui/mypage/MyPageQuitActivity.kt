package com.playtogether_android.app.presentation.ui.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityMyPageQuitBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.ThunderAppliedActivity
import com.playtogether_android.app.util.CustomDialog

class MyPageQuitActivity : BaseActivity<ActivityMyPageQuitBinding>(R.layout.activity_my_page_quit) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backBtnListener()
        quitBtnListener()
    }

    private fun backBtnListener() {
        binding.ivSettingBack.setOnClickListener {
            startActivity(Intent(this, ManageAccountActivity::class.java))
            finish()
        }
    }

    private fun quitBtnListener() {
        binding.tvMypageQuit.setOnClickListener {
            val title = "서비스를 탈퇴합니다"
            val dialog = CustomDialog(this, title)
            dialog.showChoiceDialog(R.layout.dialog_yes_no)
            dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
                override fun onClicked(num: Int) {
                    TODO("Not yet implemented")
                }

            })
        }

    }

}