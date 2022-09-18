package com.playtogether_android.app.presentation.ui.userInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityManageBlockedUserBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.userInfo.viewmodel.UserInfoViewModel
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

    private fun getBlockUserList() {
        userInfoViewModel.getBlockUserList()
    }

    private fun observeBlockUserList() {
        userInfoViewModel.blockUserList.observe(this) {
            blockedUserAdapter.blockUserList = it.data

        }
    }
}