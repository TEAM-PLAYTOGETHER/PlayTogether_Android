package com.playtogether_android.app.presentation.ui.userInfo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentMyInfoBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.mypage.MyPageSettingActivity


class MyInfoFragment : BaseFragment<FragmentMyInfoBinding> (R.layout.fragment_my_info) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moveSettingView()
    }

    //마이페이지 설정뷰로 이동
    private fun moveSettingView() {
        binding.btnSetting.setOnClickListener {
            val intent = Intent(requireActivity(), MyPageSettingActivity::class.java)
            startActivity(intent)
        }
    }

}