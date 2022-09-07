package com.playtogether_android.app.presentation.ui.userInfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityMyCrewManageBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.mypage.MyPageSettingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyCrewManageActivity : BaseActivity<ActivityMyCrewManageBinding> (R.layout.activity_my_crew_manage) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moveMyCrewDelete()
        moveBlockedUserManage()
        btnBackEvent()
    }

    // 차단사용자 관리하기 뷰 이동
    private fun moveBlockedUserManage() {
        startActivity(Intent(this, ManageBlockedUserActivity::class.java))
    }

    // 동아리 탈퇴하기 뷰 이동
    private fun moveMyCrewDelete() {
        startActivity(Intent(this, DeleteMyCrewActivity::class.java))
    }

    // 뒤로가기 버튼
    private fun btnBackEvent() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }




}