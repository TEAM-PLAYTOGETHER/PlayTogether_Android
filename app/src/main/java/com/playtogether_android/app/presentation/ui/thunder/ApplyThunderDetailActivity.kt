package com.playtogether_android.app.presentation.ui.thunder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.playtogether_android.app.R
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.databinding.ActivityApplyThunderDetailBinding
import com.playtogether_android.app.presentation.ui.home.AppliedActivity
import com.playtogether_android.app.util.CustomDialog

class ApplyThunderDetailActivity : BaseActivity<ActivityApplyThunderDetailBinding>(R.layout.activity_apply_thunder_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testData()
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
                    Toast.makeText(this@ApplyThunderDetailActivity, "신청 취소 완료", Toast.LENGTH_LONG).show()

                }
            }
        })
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
}