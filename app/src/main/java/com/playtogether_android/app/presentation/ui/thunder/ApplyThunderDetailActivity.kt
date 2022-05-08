package com.playtogether_android.app.presentation.ui.thunder

import android.os.Bundle
import android.widget.Toast
import com.playtogether_android.app.R
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.databinding.ActivityApplyThunderDetailBinding
import com.playtogether_android.app.util.CustomDialog

class ApplyThunderDetailActivity : BaseActivity<ActivityApplyThunderDetailBinding>(R.layout.activity_apply_thunder_detail) {

    private lateinit var applicantListAdapter: ApplicantListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testData()
        initAdapter()
        binding.tvCancelApplication.setOnClickListener {
            showCancelDialog()
        }
    }

    private fun showCancelDialog() {
        val title = "신청을 취소할까요?"
        val dialog = CustomDialog(this, title)
        dialog.showChoiceDialog(R.layout.dialog_yes_no)
        dialog.setOnClickedListener(object:CustomDialog.ButtonClickListener {
            override fun onClicked(num: Int) {
                if(num==1) {
                    showConfirmDialog()
                    Toast.makeText(this@ApplyThunderDetailActivity, "신청취소완료", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun showConfirmDialog() {
        val title = "신청 취소되었습니다."
        val dialog = CustomDialog(this, title)
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