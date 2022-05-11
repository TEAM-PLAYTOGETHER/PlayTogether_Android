package com.playtogether_android.app.presentation.ui.thunder

import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityApplyThunderDetailBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderDetailViewModel
import com.playtogether_android.app.util.CustomDialog
import com.playtogether_android.app.util.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApplyThunderDetailActivity :
    BaseActivity<ActivityApplyThunderDetailBinding>(R.layout.activity_apply_thunder_detail) {

    private lateinit var applicantListAdapter: ApplicantListAdapter
    private val thunderDetailViewModel: ThunderDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val thunderId = intent.getStringExtra("thunderId")
        testData()
        initAdapter()

        binding.tvCancelApplication.setOnClickListener {
            showCancelDialog(thunderId!!)
        }
    }

    private fun showCancelDialog(thunderId: String) {
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
                        } else {
                            shortToast("실패")
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


    private fun testData() {
        with(binding) {
            tvApplythunderdetailOpenerName.text = "문수제비"
            tvApplythunderdetailTitle.text = "우리집에서 피자 먹기"
            tvApplythunderdetailDateContent.text = "2022.04.15"
            tvApplythunderdetailTimeContent.text = "18:00 ~"
            tvApplythunderdetailPlaceContent.text = "우리집"
            tvApplythunderdetailCategoryContent.text = "음식"
            tvApplythunderdetailDescription.text = "스크롤뷰 적용 후 스티링 더 길게 테스트 예정임다"
            tvCurrentApplicant.text = "2"
            tvMaxApplicant.text = "6"
        }
    }

    private fun initAdapter() {
        applicantListAdapter = ApplicantListAdapter()

        binding.rvThunderApplicantList.adapter = applicantListAdapter

        applicantListAdapter.applicantList = listOf(
            TempApplicantData.UserList("김세후니", 25, "ENFJ"),
            TempApplicantData.UserList("권용민 바보", 26, "ESFJ"),
            TempApplicantData.UserList("김세후니", 25, "ENFJ"),
            TempApplicantData.UserList("권용민 바보", 26, "ESFJ"),
            TempApplicantData.UserList("김세후니", 25, "ENFJ"),
            TempApplicantData.UserList("권용민 바보", 26, "ESFJ"),
            TempApplicantData.UserList("권용민 바보", 26, "ESFJ")
        )

        applicantListAdapter.notifyDataSetChanged()
    }


}