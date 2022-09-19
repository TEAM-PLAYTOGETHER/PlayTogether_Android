package com.playtogether_android.app.presentation.ui.userInfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityManageBlockedUserBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.userInfo.viewmodel.UserInfoViewModel
import com.playtogether_android.app.util.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageBlockedUserActivity : BaseActivity<ActivityManageBlockedUserBinding>(R.layout.activity_manage_blocked_user) {

    private lateinit var blockedUserAdapter: BlockedUserAdapter

    private val userInfoViewModel: UserInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickEvent()
        initAdapter()
        getBlockUserList()
        observeBlockUserList()
        delUnblockUser()
    }

    private fun clickEvent() {
        btnBackEvent()
    }

    // 뒤로가기 버튼
    private fun btnBackEvent() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {
        blockedUserAdapter = BlockedUserAdapter()
        binding.rvBlockedUserList.adapter = blockedUserAdapter

    }

    // 유저 차단 리스트 조회
    private fun getBlockUserList() {
        userInfoViewModel.getBlockUserList()
    }

    private fun observeBlockUserList() {
        userInfoViewModel.blockUserList.observe(this) {
            blockedUserAdapter.blockUserList = it.data
        }
    }

    // 유저 차단 해제
    private fun delUnblockUser() {
        blockedUserAdapter.setIsMemberId { isMemberId ->
            userInfoViewModel.delUnblockUser(isMemberId)
            onStart()
//            userInfoViewModel.getBlockUserList()
            // 새로고침
            val intent = Intent(this, ManageBlockedUserActivity::class.java)
            finish()
            overridePendingTransition(0,0) //인텐트 애니메이션 없애기
            startActivity(intent)
            overridePendingTransition(0,0)

//            shortToast("차단 해제가 완료되었습니다.")


        }
    }
}