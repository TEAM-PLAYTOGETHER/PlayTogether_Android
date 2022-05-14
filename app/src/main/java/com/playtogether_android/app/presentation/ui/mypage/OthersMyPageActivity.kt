package com.playtogether_android.app.presentation.ui.mypage

import android.os.Bundle
import android.util.Log
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOthersMyPageBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.mypage.viewModel.MyPageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OthersMyPageActivity :
    BaseActivity<ActivityOthersMyPageBinding>(R.layout.activity_others_my_page) {

    private val myPageViewModel: MyPageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPersonalInfo()
        initBackBtn()
    }

    //내 정보 서버통신
    private fun initPersonalInfo() {
        // showLoading()
        myPageViewModel.userCheck.observe(this) {
            myPageViewModel.getUserCheck(it.userLoginId)
        }

        val userLoginId = intent.getStringExtra("organizerId").toString()
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
}