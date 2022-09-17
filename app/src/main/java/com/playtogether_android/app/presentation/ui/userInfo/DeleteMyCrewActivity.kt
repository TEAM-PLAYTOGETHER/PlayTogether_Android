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
import com.playtogether_android.app.util.CustomDialog
import com.playtogether_android.data.singleton.PlayTogetherRepository.crewId
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteMyCrewActivity : BaseActivity<ActivityDeleteMyCrewBinding>(R.layout.activity_delete_my_crew) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fontColorHighlight()
        btnBackEvent()
    }

    private fun fontColorHighlight() {
        val strTitle = SpannableStringBuilder(binding.tvDeleteCrewTitle.text)
        strTitle.setSpan(ForegroundColorSpan(getColor(R.color.red_FF0000)), 10, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvDeleteCrewTitle.text = strTitle

        val strContent = SpannableStringBuilder(binding.tvDeleteCrewContent.text)
        strContent.setSpan(ForegroundColorSpan(getColor(R.color.red_FF0000)), 98, 115, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvDeleteCrewContent.text = strContent

    }

    // 뒤로가기 버튼
    private fun btnBackEvent() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    // 탈퇴하기 버튼 클릭 이벤트
    private fun btnDeleteEvent() {
        binding.btnDeleteCrew.setOnClickListener {
//            showConfirmDialog()
        }
    }

    // 탈퇴 되묻는 다이어로그
//    private fun showConfirmDialog() {
//        val title = "정말 동아리를\n탈퇴하시겠어요?"
//        val dialog = CustomDialog(this, title)
//        dialog.showChoiceDialog(R.layout.dialog_yes_no)
//        dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
//            override fun onClicked(num: Int) {
//                if (num == 1) {
//                    userInfoViewModel.delCrew(crewId)
//                    userInfolViewModel.isConfirm.observe(this@DeleteMyCrewActivity) { success ->
//                        if (success) {
//                            showFinishDialog()
//                        }
//                    }
//                }
//            }
//        })
//    }

    // 탈퇴 완료 다이어로그
    private fun showFinishDialog() {
        val title = "탈퇴 완료되었습니다."
        val dialog = CustomDialog(this@DeleteMyCrewActivity, title)
        dialog.showConfirmDialog(R.layout.dialog_check)
    }

}