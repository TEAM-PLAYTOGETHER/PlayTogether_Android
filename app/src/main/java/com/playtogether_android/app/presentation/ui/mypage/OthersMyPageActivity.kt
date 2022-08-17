package com.playtogether_android.app.presentation.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOthersMyPageBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.message.ChattingActivity
import com.playtogether_android.app.presentation.ui.mypage.viewModel.MyPageViewModel
import com.playtogether_android.app.util.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OthersMyPageActivity :
    BaseActivity<ActivityOthersMyPageBinding>(R.layout.activity_others_my_page) {

    private val myPageViewModel: MyPageViewModel by viewModels()
    private var organizerId: Int = -1
    private var roomId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPersonalInfo()
        initBackBtn()

        getIntentData()
        observeRoomId()
        clickSendMessage()
    }

    //내 정보 서버통신
    private fun initPersonalInfo() {
        // showLoading()
        val userLoginId = intent.getStringExtra("userLoginId").toString()
        Log.d("testUserIdTest", "" + userLoginId)

        myPageViewModel.getUserCheck(userLoginId)
        myPageViewModel.userCheck.observe(this) {
            binding.user = it
        }
    }

    private fun initBackBtn() {
        binding.ivOtherMypageBack.setOnClickListener {
            finish()
        }
    }

    private fun getIntentData() {
        organizerId = intent.getIntExtra("organizerId", -1)
        myPageViewModel.getRoomId(organizerId)
    }

    private fun observeRoomId() {
        myPageViewModel.roomId.observe(this) {
            roomId = it
        }
    }

    private fun clickSendMessage() {
        binding.ivOtherMypageMessage.setOnClickListener {
            val name = binding.tvOtherMypageTitleName.text.toString()
            val intent = Intent(this, ChattingActivity::class.java)
            intent.putExtra("audienceId", organizerId)
            intent.putExtra("roomId", roomId)
            intent.putExtra("name", name)
            if (roomId != -1) startActivity(intent)
            else shortToast("실패했습니다")
        }
    }
}