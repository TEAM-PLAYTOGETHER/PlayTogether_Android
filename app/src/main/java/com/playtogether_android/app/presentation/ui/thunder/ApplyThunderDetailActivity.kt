package com.playtogether_android.app.presentation.ui.thunder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityApplyThunderDetailBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.message.ChattingActivity
import com.playtogether_android.app.presentation.ui.mypage.OthersMyPageActivity
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderDetailViewModel
import com.playtogether_android.app.util.CustomDialog
import com.playtogether_android.app.util.imageNullCheck
import com.playtogether_android.app.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels

@AndroidEntryPoint
class ApplyThunderDetailActivity :
    BaseActivity<ActivityApplyThunderDetailBinding>(R.layout.activity_apply_thunder_detail) {

    private val thunderDetailViewModel: ThunderDetailViewModel by viewModels()
    private lateinit var applicantListAdapter: ApplicantListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val thunderId = intent.getIntExtra("thunderId", -1)
        initData(thunderId)
        initAdapter()
        binding.tvCancelApplication.setOnClickListener {
            showCancelDialog(thunderId)
        }
        setClickListener()
    }

    private fun initData(thunderId: Int) {
        thunderDetailViewModel.thunderDetail(thunderId)
        thunderDetailViewModel.thunderDetailMember(thunderId)
        thunderDetailViewModel.thunderDetailOrganizer(thunderId)

        thunderDetailViewModel.detailItemList.observe(this) {
            binding.detailData = it
            imageNullCheck(it.image, binding.ivApplythunderdetailImage)
        }
        thunderDetailViewModel.organizerInfo.observe(this) {
            binding.organizer = it
        }
    }

    private fun setClickListener() {

        binding.clThunderOpenerMessage.setOnClickListener {
//           쪽지 보내기로 이동
            var organizerId = -1
            val thunderId = intent.getIntExtra("thunderId", -1)
            thunderDetailViewModel.organizerInfo.observe(this) {
                organizerId = it.organizerId
            }
            val intent = Intent(this, ChattingActivity::class.java)
            intent.putExtra("organizerId", organizerId)
            intent.putExtra("thunderId", thunderId)
            startActivity(intent)
        }

        // 뒤로가기 버튼
        binding.ivApplythunderdetailBack.setOnClickListener {
            finish()
        }

        // TODO: 혜빈아 요기!!!!!!!!! 일단 코드가 지저분하지만.. 나중에 정리할게ㅋㅋㅋ
        // 개설자 프로필로 이동
        binding.ivOpenerProfile.setOnClickListener {
            var userLoginId: String? = null
            var organizerName: String? = null
            var organizerId: Int? = null

            thunderDetailViewModel.organizerInfo.observe(this) {
                userLoginId = it.userLoginId.toString()
                organizerId = it.organizerId
                organizerName = it.name
            }
            var intent = Intent(this, OthersMyPageActivity::class.java)
            intent.putExtra("userLoginId", userLoginId)
            intent.putExtra("organizerId", organizerId)
            intent.putExtra("organizerName", organizerName)

            startActivity(intent)
        }
    }

    private fun showCancelDialog(thunderId: Int) {
        val title = "신청을 취소할까요?"
        val dialog = CustomDialog(this, title)
        dialog.showChoiceDialog(R.layout.dialog_yes_no)
        dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
            override fun onClicked(num: Int) {
                if (num == 1) {
                    thunderDetailViewModel.joinAndCancel(thunderId)
                    thunderDetailViewModel.isConfirm.observe(this@ApplyThunderDetailActivity) { success ->
                        if (success) {
                            showConfirmDialog()
                        }
                    }
                }
            }
        })
    }


    private fun showConfirmDialog() {
        val title = "신청 취소되었습니다."
        val dialog = CustomDialog(this@ApplyThunderDetailActivity, title)
        dialog.showConfirmDialog(R.layout.dialog_check)
    }


    private fun initAdapter() {
        applicantListAdapter = ApplicantListAdapter()
        binding.rvThunderApplicantList.adapter = applicantListAdapter

        thunderDetailViewModel.memberList.observe(this) {
            applicantListAdapter.applicantList.addAll(it)
            applicantListAdapter.notifyDataSetChanged()
        }
    }
}