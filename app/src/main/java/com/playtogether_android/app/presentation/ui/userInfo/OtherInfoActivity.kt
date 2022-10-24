package com.playtogether_android.app.presentation.ui.userInfo

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOtherInfoBinding
import com.playtogether_android.app.databinding.DialogCheckBinding
import com.playtogether_android.app.databinding.DialogYesNoBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.main.MainActivity
import com.playtogether_android.app.presentation.ui.main.WebViewActivity
import com.playtogether_android.app.presentation.ui.message.ChattingActivity
import com.playtogether_android.app.presentation.ui.userInfo.viewmodel.UserInfoViewModel
import com.playtogether_android.app.util.CustomDialogSon
import com.playtogether_android.app.util.DateTimeUtil
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.app.util.showCustomPopUp
import com.playtogether_android.data.singleton.PlayTogetherRepository
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class OtherInfoActivity : BaseActivity<ActivityOtherInfoBinding>(R.layout.activity_other_info) {

    private val userInfoViewModel: UserInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val memberId = intent.getIntExtra("memberId", -1)
        val crewId = PlayTogetherRepository.crewId
        clickEvent()
        getOtherInfo(crewId, memberId)
        initData()
        btnOption(memberId)
        Timber.d("initOtherInfo : $crewId, $memberId")
    }

    private fun clickEvent() {
        moveChattingView()
        btnBackEvent()
    }

    // 채팅뷰로 이동
    private fun moveChattingView() {
        binding.clOtherInfoBtnChatting.setOnClickListener {
            val recvId: Int? = userInfoViewModel.otherInfoData.value?.id?.toInt()
            val roomId: Int? = userInfoViewModel.roomId
            val name: String? = userInfoViewModel.otherInfoData.value?.nickname

            if ((recvId == null) or (roomId == null) or (name == null)) {
                shortToast("오류가 발생했습니다. 다시 시도해주세요")
                return@setOnClickListener
            }

            val intent = Intent(this, ChattingActivity::class.java)
            intent.putExtra("audienceId", recvId)
            intent.putExtra("roomId", roomId)
            intent.putExtra("name", name)
            startActivity(intent)
        }
    }

    // 뒤로가기 버튼
    private fun btnBackEvent() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun initIntent(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }

    // 차단/신고 옵션 버튼
    //todo 옵션 차단/신고 기능
    private fun btnOption(memberId: Int) {
        binding.btnOption.setOnClickListener {
            val popup = showCustomPopUp(it, R.array.option_mypage, baseContext)
            popup.setOnItemClickListener { _, view, _, _ ->
                if ((view as TextView).text == "차단") {
                    //todo 차단 다이어로그 띄우기 -> 그 안에 차단 API 연결
//                    with(userInfoViewModel) {
//                        postBlockUser(memberId)
//                        isBlock.observe(this@OtherInfoActivity) {
//                            if (it) {
//                                popup.dismiss()
//
//                            }
//                        }
//                    }
                    showBlockDialog(memberId)
                    popup.dismiss()
                } else {
                    initIntent("https://cheddar-liquid-051.notion.site/14fc6c632471488486e7e76bc161069e")
                    popup.dismiss()

                }
            }
            popup.show()
        }
    }

    // 차단 다이어로그
    private fun showBlockDialog(memberId: Int) {
        userInfoViewModel.otherInfoData.observe(this) {
            val nickname = it.nickname

            val title = "${nickname}님을 차단할까요?\n[내 동아리 관리하기]에서\n해제할 수 있습니다!"
            val dialog = CustomDialogSon(this)
            val view = DialogYesNoBinding.inflate(layoutInflater)
            dialog.setContentView(view.root)

            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
            dialog.show()

            with(view) {
                tvDialogTitle.text = title
                tvDialogNo.setOnClickListener {
                    dialog.dismiss()
                }
                tvDialogYes.setOnClickListener {
                    //todo 유저차단 API 연결
                    with(userInfoViewModel) {
                        postBlockUser(memberId)
                        isBlock.observe(this@OtherInfoActivity) {
                            if (it) {
                                showConfirmDialog(dialog)
                            }
                        }
                    }
                    dialog.dismiss()
                }
            }
        }
    }

    private fun showConfirmDialog(dialog: CustomDialogSon) {
        val title = "차단 완료되었습니다."
        val view = DialogCheckBinding.inflate(layoutInflater)
        dialog.setContentView(view.root)
        dialog.show()
        view.tvDialogTitle.text = title
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        view.tvDialogCheck.setOnClickListener {
            dialog.dismiss()
            // 차단 완료 후 홈뷰로 이동
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun getOtherInfo(crewId: Int, memberId: Int) {
        userInfoViewModel.getOtherInfo(crewId, memberId)
    }

    private fun initData() {
        userInfoViewModel.otherInfoData.observe(this) {
            val birth = DateTimeUtil.convertBirthFormat(it.birth)
            val gender = it.gender
            val genderFormat: String
            if (gender == "남") genderFormat = "M"
            else genderFormat = "W"

            userInfoViewModel.getChattingRoomId(it.id.toInt())
            binding.birthAndGender = "${birth}년생 ・ $genderFormat"
            binding.otherInfo = it
            binding.tvOtherInfoTitle.text = "${it.nickname}님의 프로필"
            binding.tvOtherInfoChatting.text = "${it.nickname}님과 채팅하기"

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

    private fun loadImage(url: String?) {

        Glide.with(this)
            .load(url)
//            .apply(RequestOptions.bitmapTransform(com.bumptech.glide.load.resource.bitmap.RoundedCorners(10)))
            .placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
            .fallback(R.drawable.ic_profile)
            .into(binding.ivProfileImg)

//          프로필 이미지 코너 라운딩 (radius: 10dp)
        binding.ivProfileImg.background =
            getResources().getDrawable(R.drawable.rectangle_radius_10, null)
        binding.ivProfileImg.setClipToOutline(true)
    }
}