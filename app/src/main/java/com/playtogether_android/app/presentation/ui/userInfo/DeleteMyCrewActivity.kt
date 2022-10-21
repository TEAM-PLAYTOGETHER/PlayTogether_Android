package com.playtogether_android.app.presentation.ui.userInfo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.text.set
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityDeleteMyCrewBinding
import com.playtogether_android.app.databinding.DialogCheckBinding
import com.playtogether_android.app.databinding.DialogYesNoBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.main.SplashActivity
import com.playtogether_android.app.presentation.ui.onboarding.OnboardingReDownLoadActivity
import com.playtogether_android.app.presentation.ui.userInfo.viewmodel.UserInfoViewModel
import com.playtogether_android.app.util.CustomDialog
import com.playtogether_android.app.util.CustomDialogSon
import com.playtogether_android.data.singleton.PlayTogetherRepository.crewId
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DeleteMyCrewActivity : BaseActivity<ActivityDeleteMyCrewBinding>(R.layout.activity_delete_my_crew) {

    private val userInfoViewModel: UserInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fontColorHighlight()
        btnBackEvent()
        btnDeleteEvent()

        val crewName = intent.getStringExtra("crewName")
        binding.tvCrewName.text = crewName
        Timber.d("crewName받는쪽 : $crewName")
    }

    private fun fontColorHighlight() {
        val strTitle = SpannableStringBuilder(binding.tvDeleteCrewTitle.text)
        strTitle.setSpan(ForegroundColorSpan(getColor(R.color.red_FF0000)), 5, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
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
            showDeleteDialog()
        }
    }

    private fun showDeleteDialog() {
        val title = "정말 동아리를\n탈퇴하시겠어요?"
        val dialog = CustomDialogSon(this)
        val view = DialogYesNoBinding.inflate(layoutInflater)
        dialog.setContentView(view.root)

        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        dialog.show()

        with(view) {
            tvDialogTitle.text = title
            tvDialogNo.setOnClickListener {
                dialog.dismiss()
            }
            tvDialogYes.setOnClickListener {
                //todo 동아리 탈퇴 API 연결
                with(userInfoViewModel) {
                    delCrew()
                    isDelete.observe(this@DeleteMyCrewActivity) {
                        if (it) {
                            showConfirmDialog(dialog)
                        }
                    }
                }
                dialog.dismiss()
            }
        }
    }

    private fun showConfirmDialog(dialog: CustomDialogSon) {
        val title =  "탈퇴 되었습니다."
        val view = DialogCheckBinding.inflate(layoutInflater)
        dialog.setContentView(view.root)
        dialog.show()
        view.tvDialogTitle.text = title
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        view.tvDialogCheck.setOnClickListener {
            dialog.dismiss()
            finish()
            //todo 탈퇴완료하고 어디로 넘어갈까????
            startActivity(Intent(this, OnboardingReDownLoadActivity::class.java))
        }
    }



}