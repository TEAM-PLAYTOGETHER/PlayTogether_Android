package com.playtogether_android.app.presentation.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOthersMyPageBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.message.ChattingActivity
import com.playtogether_android.app.presentation.ui.mypage.viewModel.MyPageViewModel
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderDetailViewModel
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OthersMyPageActivity :
    BaseActivity<ActivityOthersMyPageBinding>(R.layout.activity_others_my_page) {

    private val myPageViewModel: MyPageViewModel by viewModels()
    private val thunderDetailViewModel: ThunderDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPersonalInfo()
        initBackBtn()
        setClickListener()
    }

    //내 정보 서버통신
    private fun initPersonalInfo() {
        // showLoading()
        myPageViewModel.userCheck.observe(this) {
            myPageViewModel.getUserCheck(it.userLoginId)
        }

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

    private fun setClickListener() {
        val organizerId = intent.getIntExtra("organizerId", -1)
        val organizerName = intent.getStringExtra("organizerName")
        binding.ivOtherMypageMessage.setOnClickListener {
//           쪽지 보내기로 이동
            var organizerId = organizerId
            var name = organizerName
            val intent = Intent(this, ChattingActivity::class.java)
            intent.putExtra("audienceId", organizerId)
            intent.putExtra("name", name)
            startActivity(intent)
        }


    }
}