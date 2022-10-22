package com.playtogether_android.app.presentation.ui.userInfo

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.RoundedCorner
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.model.content.RoundedCorners
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.FragmentMyInfoBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import com.playtogether_android.app.presentation.ui.home.view.HomeFragmentDialog
import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.playtogether_android.app.presentation.ui.main.WebViewActivity
import com.playtogether_android.app.presentation.ui.mypage.MyPageSettingActivity
import com.playtogether_android.app.presentation.ui.userInfo.viewmodel.UserInfoViewModel
import com.playtogether_android.app.util.DateTimeUtil
import com.playtogether_android.app.util.GalleryUtil
import com.playtogether_android.app.util.MultiPartResolver
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.data.singleton.PlayTogetherRepository
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MyInfoFragment : BaseFragment<FragmentMyInfoBinding>(R.layout.fragment_my_info) {

    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var intentLauncher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMyInfo()
        observeMyInfo()
        initBottomDialog()
        clickEvent()
        imagePickerCallback()

    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Timber.d("권한 승인")
                imagePicker()
//                uploadImageListener()
            } else
                requireActivity().shortToast("권한요청이 거절되었습니다.")
        }

    private fun clickEvent() {
        moveSettingView()
        moveEditProfile()
        moveManageCrew()
        moveWebPage()
        moveEditProfileImage()
    }

    //마이페이지 설정뷰로 이동
    private fun moveSettingView() {
        binding.btnSetting.setOnClickListener {
            val intent = Intent(requireActivity(), MyPageSettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        //여기에 프로필 서버통신하는 코드 넣기
        getMyInfo()
        observeMyInfo()
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
            binding.homeViewModel = homeViewModel
            binding.nickname = it.nickname
            binding.description = it.description

            // 지하철 미지정 시 '지하철역 미지정' 하나만 띄우기
            if (it.firstStation == null) {
                binding.firstStation = "지하철역 미지정"
                binding.isEmpty = true
            } else if (it.secondStation == null) {
                binding.firstStation = it.firstStation
                binding.isEmpty = true
            } else
                binding.firstStation = it.firstStation
            binding.secondStation = it.secondStation

            //프로필 이미지 띄우기
            val imageUrl = it.profileImage
            loadImage(imageUrl)


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
            val intent = Intent(requireActivity(), EditProfileActivity::class.java)
            val list = arrayListOf<String>()
            Timber.e("121111 : ${binding.firstStation}")
            Timber.e("121111 : ${binding.secondStation}")
            if (binding.firstStation != null) {
                list.add(binding.firstStation.toString())
            }
            if (binding.secondStation != null) {
                list.add(binding.secondStation.toString())
            }
            if (list != null) {
                if (list.size != 0) {
                    intent.putExtra("ChipList", list)
                }
            }
            intent.putExtra("crewName", PlayTogetherRepository.crewName)
            intent.putExtra("nickname", binding.nickname)
            intent.putExtra("description", binding.description)
            intent.putExtra("firstStation", binding.firstStation)
            intent.putExtra("secondStation", binding.secondStation)
            //todo 온보딩뷰 이동 시 넘겨줄 값 추가
            startActivity(intent)
        }
    }

    //내 동아리 관리하기 이동뷰 이동
    private fun moveManageCrew() {
        binding.tvCrewManage.setOnClickListener {
            userInfoViewModel.myInfoData.observe(viewLifecycleOwner) {
                val intent = Intent(requireActivity(), MyCrewManageActivity::class.java)
                intent.putExtra("crewName", it.crewName)
                Timber.d("crewName보내는 쪽: ${it.crewName}")
                startActivity(intent)
            }
            //todo 동아리 탈퇴를 위해 crewId 넘겨줘야 함?

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
            initIntent("https://cheddar-liquid-051.notion.site/9386d123057d48ceb8a8f71ebb800888")
        }
        //이벤트
        binding.tvEvent.setOnClickListener {
            initIntent("https://cheddar-liquid-051.notion.site/ceff1d48f36847a19b50b5dc663f30b0")
        }
        //문의하기
        binding.tvQuestion.setOnClickListener {
            initIntent("http://pf.kakao.com/_RDWlxj")
        }
    }

    //todo 프로필 이미지 추가하기 뷰
    private fun moveEditProfileImage() {
        val util = GalleryUtil(requireActivity(), requestPermissionLauncher)
        binding.btnEditphoto.setOnClickListener {
            if (util.aboutPermission()) {
                //todo upload image method
                imagePicker()
            }
            //todo 이동할 곳과 넘겨줄 값 추가
            //todo crewId값 넘겨줘야 함?
        }
    }

    private fun loadImage(url: String?) {

        Glide.with(this)
            .load(url)
//            .apply(RequestOptions.bitmapTransform(com.bumptech.glide.load.resource.bitmap.RoundedCorners(10)))
            .placeholder(R.drawable.img_go)
            .error(R.drawable.img_go)
            .fallback(R.drawable.img_go)
            .into(binding.ivProfileImg)

//          프로필 이미지 코너 라운딩 (radius: 10dp)
        binding.ivProfileImg.background =
            getResources().getDrawable(R.drawable.rectangle_radius_10, null)
        binding.ivProfileImg.setClipToOutline(true)
    }

    private fun imagePicker() {
        Intent(Intent.ACTION_PICK).apply {
            type = MediaStore.Images.Media.CONTENT_TYPE
            data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
            requireActivity().setResult(RESULT_OK)
            intentLauncher.launch(this)
        }
    }

    private fun imagePickerCallback() {
        intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                val item = it.data?.clipData
                if (item != null) {
                    val multipartResolver = MultiPartResolver(requireActivity())
                    val transferItem = multipartResolver.createImgMultiPart(item.getItemAt(0).uri)
                    userInfoViewModel.putProfileImage(transferItem)
                }
            } else {
                Timber.e("profileImageError : 이상함")
            }
        }
    }

}