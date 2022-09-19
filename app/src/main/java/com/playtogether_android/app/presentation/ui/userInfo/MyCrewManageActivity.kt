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

        clickEvent()
    }

    private fun clickEvent() {
        moveMyCrewDelete()
        moveBlockedUserManage()
        btnBackEvent()
    }

    // 차단사용자 관리하기 뷰 이동
    private fun moveBlockedUserManage() {
        binding.tvManageBlockedUser.setOnClickListener {
            startActivity(Intent(this, ManageBlockedUserActivity::class.java))
        }
    }

    // 동아리 탈퇴하기 뷰 이동
    private fun moveMyCrewDelete() {
        binding.tvDeleteCrew.setOnClickListener {
            val crewName = intent.getStringExtra("crewName")
            val intent = Intent(this, DeleteMyCrewActivity::class.java)
            intent.putExtra("crewName", crewName)
            startActivity(intent)
        }
    }

    // 뒤로가기 버튼
    private fun btnBackEvent() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }



}