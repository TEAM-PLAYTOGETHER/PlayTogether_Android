package com.playtogether_android.app.presentation.ui.thunder

import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityReportPostFinishBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class ReportPostFinishActivity : BaseActivity<ActivityReportPostFinishBinding>(R.layout.activity_report_post_finish) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            clickEvent()
    }

    private fun clickEvent() {
        btnExitEvent()
        btnOkEvent()
    }

    private fun btnExitEvent() {
        binding.btnExit.setOnClickListener {
            finish()
        }
    }

    private fun btnOkEvent() {
        binding.btnOk.setOnClickListener {
            finish()
        }
    }
}