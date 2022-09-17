package com.playtogether_android.app.presentation.ui.userInfo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentMyInfoBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.home.view.HomeFragmentDialog
import com.playtogether_android.app.presentation.ui.main.WebViewActivity
import com.playtogether_android.app.presentation.ui.mypage.MyPageSettingActivity
import com.playtogether_android.app.presentation.ui.onboarding.OnBoardingIntroduceActivity
import com.playtogether_android.app.presentation.ui.userInfo.viewmodel.UserInfoViewModel
import com.playtogether_android.app.util.DateTimeUtil
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.userInfo.MyInfoData
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class MyInfoFragment : BaseFragment<FragmentMyInfoBinding>(R.layout.fragment_my_info) {

    private val userInfoViewModel: UserInfoViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMyInfo()
        observeMyInfo()
        initBottomDialog()
        moveSettingView()
        moveEditProfile()
        moveManageCrew()
        moveWebPage()
    }

    //마이페이지 설정뷰로 이동
    private fun moveSettingView() {
        binding.btnSetting.setOnClickListener {
            val intent = Intent(requireActivity(), MyPageSettingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getMyInfo() {
        userInfoViewModel.getMyInfo()
    }

    private fun observeMyInfo() {
        userInfoViewModel.myInfoData.observe(viewLifecycleOwner) { it ->
            val birth = DateTimeUtil.convertBirthFormat(it.birth)
            val gender = it.gender
            val genderFormat: String
            if (gender == "남") genderFormat = "M"
            else genderFormat = "W"

            binding.birthAndGender = "${birth}년생 ・ $genderFormat"
            binding.myInfo = it

        }
    }

    //동아리 전환 바텀시트
    private fun initBottomDialog() {
        binding.clTouchCrewChange.setOnClickListener {
            val bottomSheetDialog = HomeFragmentDialog()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "init bottom_sheet")
        }
    }

    //프로필 수정하기 온보딩 뷰 이동
    private fun moveEditProfile() {
        binding.tvProfileEdit.setOnClickListener {
            val intent = Intent(requireActivity(), OnBoardingIntroduceActivity::class.java)
            //todo 온보딩뷰 이동 시 넘겨줄 값 추가
            startActivity(intent)
        }
    }

    //내 동아리 관리하기 이동뷰 이동
    private fun moveManageCrew() {
        binding.tvCrewManage.setOnClickListener {
            val intent = Intent(requireActivity(), MyCrewManageActivity::class.java)
            //todo 동아리 탈퇴를 위해 crewId 넘겨줘야 함?
            startActivity(intent)
        }
    }


    private fun initIntent(url: String) {
        val intent = Intent(requireActivity(), WebViewActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }

    //외부링크 이동
    private fun moveWebPage() {
        //공지사항
        binding.tvNotice.setOnClickListener {
            initIntent("https://cheddar-liquid-051.notion.site/14fc6c632471488486e7e76bc161069e")
        }
        //이벤트
        binding.tvEvent.setOnClickListener {
            initIntent("https://cheddar-liquid-051.notion.site/14fc6c632471488486e7e76bc161069e")
        }
        //문의하기
        binding.tvQuestion.setOnClickListener {
            initIntent("https://cheddar-liquid-051.notion.site/14fc6c632471488486e7e76bc161069e")
        }
    }

    //todo 프로필 이미지 추가하기 뷰
    private fun moveEditProfileImage() {
        binding.btnEditphoto.setOnClickListener {
            //todo 이동할 곳과 넘겨줄 값 추가
            //todo crewId값 넘겨줘야 함?
        }
    }


}