package com.playtogether_android.app.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderDetailBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.playtogether_android.app.presentation.ui.message.ChattingActivity
import com.playtogether_android.app.presentation.ui.mypage.OthersMyPageActivity
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderDetailViewModel
import com.playtogether_android.app.util.CustomDialog
import com.playtogether_android.app.util.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThunderDetailActivity :
    BaseActivity<ActivityThunderDetailBinding>(R.layout.activity_thunder_detail) {
    private val thunderDetailViewModel: ThunderDetailViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private var organizerId: Int = -1
    private var name: String = "null"
    private var roomId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        clickProfile()
        clickSendMessage()
        clickApply()
        clickBackArrow()

        observeOrganizer()
        observeRoomId()
    }

    private fun clickApply() {
        binding.clThunderdetailApplyBtn.setOnClickListener {
            showApplyDialog()
        }
    }

    private fun showApplyDialog() {
        val title = "번개에 참여할까요?"
        val dialog = CustomDialog(this, title)
        dialog.showChoiceDialog(R.layout.dialog_yes_no)
        dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
            override fun onClicked(num: Int) {
                if (num == 1) {
                    val thunderId = intent.getIntExtra("thunderId", -1)
                    homeViewModel.postJoinThunder(thunderId)
                    homeViewModel.joinThunder.observe(this@ThunderDetailActivity) {
                        if (it.success) {
                            val intent =
                                Intent(
                                    this@ThunderDetailActivity, ThunderAppliedActivity::class.java
                                )
                            intent.putExtra("thunderId", thunderId)
                            startActivity(intent)
                            this@ThunderDetailActivity.finish()
                        } else {
                            Log.d("번개참여", "실패")
                        }
                    }
                }
            }
        })
    }

    private fun initData() {
        val thunderId = intent.getIntExtra("thunderId", -1)

        with(thunderDetailViewModel) {
            thunderDetail(thunderId)
            thunderDetailMember(thunderId)
            thunderDetailOrganizer(thunderId)
        }
        thunderDetailViewModel.detailItemList.observe(this) {
            binding.detailData = it
//            Glide
//                .with(this)
//                .load(it.image)
//                .into(binding.ivThunderdetailIcon) 여따가 이미지 넣으면 됨
        }

        thunderDetailViewModel.organizerInfo.observe(this) {
            binding.organizer = it
        }
    }

    private fun observeOrganizer() {
        thunderDetailViewModel.organizerInfo.observe(this) {
            organizerId = it.organizerId
            name = it.name
            thunderDetailViewModel.getRoomId(organizerId)
        }
    }

    private fun observeRoomId() {
        thunderDetailViewModel.roomId.observe(this) {
            roomId = it
        }
    }

    private fun clickSendMessage() {
        binding.clThunderdetailMessage.setOnClickListener {
            val intent = Intent(this, ChattingActivity::class.java)
            intent.putExtra("audienceId", organizerId)
            intent.putExtra("roomId", roomId)
            intent.putExtra("name", name)
            if (roomId != -1) startActivity(intent)
            else shortToast("실패했습니다")
        }
    }

    private fun clickBackArrow() {
        binding.ivThunderdetailBack.setOnClickListener {
            finish()
        }
    }

    private fun clickProfile() {
        // TODO: 혜빈아 요기!!!!!!!!! 일단 코드가 지저분하지만.. 나중에 정리할게ㅋㅋㅋ
        // 개설자 프로필로 이동
        binding.ivThunderdetailIcon.setOnClickListener {
            var organizerName: String? = null
            var organizerId: Int? = null
            thunderDetailViewModel.organizerInfo.observe(this) {
                organizerId = it.organizerId
                organizerName = it.name
            }
            val intent = Intent(this, OthersMyPageActivity::class.java)
            intent.putExtra("organizerId", organizerId)
            intent.putExtra("organizerName", organizerName)

            startActivity(intent)
        }

    }


}