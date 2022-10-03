package com.playtogether_android.app.presentation.ui.thunder

import android.os.Bundle
import androidx.activity.viewModels
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
    }

    private fun clickEvent() {
        btnBackEvent()
    }

    // 뒤로가기 버튼
    private fun btnBackEvent() {
        binding.btnBack.setOnClickListener {
            finish()
            //todo 신고완료 뷰 만들어서 이동 넣기
//            moveReportFinishView()
        }
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
                shortToast("신고 내용이 접수되었습니다.")
            } else {
                shortToast("게시글 신고에 실패하였습니다.")
            }
        }
    }
}