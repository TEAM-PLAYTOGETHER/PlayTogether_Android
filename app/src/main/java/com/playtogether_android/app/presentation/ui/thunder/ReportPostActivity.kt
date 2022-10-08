package com.playtogether_android.app.presentation.ui.thunder

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityReportPostBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderViewModel
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.domain.model.thunder.ReportData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportPostActivity : BaseActivity<ActivityReportPostBinding>(R.layout.activity_report_post) {

    private val thunderViewModel: ThunderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val thunderId = intent.getIntExtra("thunderId", -1)

        clickEvent()
        postReport(thunderId)
        checkReportContent()
    }

    private fun clickEvent() {
        btnBackEvent()
    }

    // 뒤로가기 버튼
    private fun btnBackEvent() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun moveReportFinishView() {
        val intent = Intent(this, ReportPostFinishActivity::class.java)
        startActivity(intent)
    }

    // 번개 게시글 신고 서버통신
    private fun postReport(thunderId: Int) {
        binding.tvReportFinish.setOnClickListener {
            thunderViewModel.requestReport.report =
                binding.etReportContent.text.toString()

            thunderViewModel.postReport(
                thunderId,
                ReportData( thunderViewModel.requestReport.report)
            )
            observeReport()
        }
    }

    private fun observeReport() {
        thunderViewModel.reportPost.observe(this) {
            if (it.success) {
                moveReportFinishView()
            } else {
                shortToast("게시글 신고에 실패하였습니다.")
            }
        }
    }

    private fun btnActive() {
        with(binding) {
            if (etReportContent.text.toString().isEmpty()) {
                tvReportFinish.isClickable = false
                tvReportFinish.setTextColor(
                    ContextCompat.getColor(
                        this@ReportPostActivity,
                        R.color.gray_gray01
                    )
                )
            } else {
                tvReportFinish.isClickable = true
                tvReportFinish.setTextColor(
                    ContextCompat.getColor(
                        this@ReportPostActivity,
                        R.color.main_green
                    )
                )
            }
        }
    }

    private fun checkReportContent() {
        binding.etReportContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                btnActive()
            }
        })
    }
}