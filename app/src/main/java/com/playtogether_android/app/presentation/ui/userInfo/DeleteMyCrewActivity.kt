package com.playtogether_android.app.presentation.ui.userInfo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.core.text.set
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityDeleteMyCrewBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class DeleteMyCrewActivity : BaseActivity<ActivityDeleteMyCrewBinding>(R.layout.activity_delete_my_crew) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fontColorHighlight()
    }

    private fun fontColorHighlight() {
        val strTitle = SpannableStringBuilder(binding.tvDeleteCrewTitle.text)
        strTitle.setSpan(ForegroundColorSpan(getColor(R.color.red_FF0000)), 10, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvDeleteCrewTitle.text = strTitle

        val strContent = SpannableStringBuilder(binding.tvDeleteCrewContent.text)
        strContent.setSpan(ForegroundColorSpan(getColor(R.color.red_FF0000)), 98, 114, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvDeleteCrewContent.text = strContent

    }
}