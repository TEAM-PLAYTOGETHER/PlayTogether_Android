package com.playtogether_android.app.presentation.ui.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityMyPageQuitBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.ThunderAppliedActivity
import com.playtogether_android.app.presentation.ui.login.LoginActivity
import com.playtogether_android.app.presentation.ui.main.SplashActivity
import com.playtogether_android.app.presentation.ui.mypage.viewModel.MyPageViewModel
import com.playtogether_android.app.util.CustomDialog
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MyPageQuitActivity : BaseActivity<ActivityMyPageQuitBinding>(R.layout.activity_my_page_quit) {

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backBtnListener()
        quitBtnListener()
        quitNetwork()
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
                    if (num == 1) {
                        myPageViewModel.deleteUser()


                    } else {
                        Timber.e("탈퇴 실패")
                    }
                }

            })
        }
    }

    private fun quitNetwork() {
        myPageViewModel.deleteUser.observe(this) {
            if(it.success) {
                val intent = Intent(this, SplashActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, "탈퇴가 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }

        }
    }

}