package com.playtogether_android.app.presentation.ui.thunder

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOpenThunderDetailBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.util.CustomDialog

class OpenThunderDetailActivity : BaseActivity<ActivityOpenThunderDetailBinding>(R.layout.activity_open_thunder_detail) {

    private lateinit var applicantListAdapter: ApplicantListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        testData()
        initAdapter()

        binding.ivOpenthunderdetailOption.setOnClickListener {
            showOptionPopup(binding.ivOpenthunderdetailOption)
        }
    }

    private fun showDeleteDialog() {
        val title = "게시글을 삭제할까요?"
        val dialog = CustomDialog(this, title)
        dialog.showChoiceDialog(R.layout.dialog_yes_no)
        dialog.setOnClickedListener(object:CustomDialog.ButtonClickListener {
            override fun onClicked(num: Int) {
                if(num==1) {
                    showConfirmDialog()
                    Toast.makeText(this@OpenThunderDetailActivity, "게시글삭제완료", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun showConfirmDialog() {
        val title = "게시글이 삭제되었습니다."
        val dialog = CustomDialog(this, title)
        dialog.showConfirmDialog(R.layout.dialog_check)
    }

    private fun testData() {
        with(binding) {
            tvApplythunderdetailOpenerName.text = "문수제비"
            tvOpenthunderdetailTitle.text = "우리집에서 피자 먹기"
            tvOpenthunderdetailDateContent.text = "2022.04.15"
            tvOpenthunderdetailTimeContent.text = "18:00 ~"
            tvOpenthunderdetailPlaceContent.text = "우리집"
            tvOpenthunderdetailCategoryContent.text = "음식"
            tvOpenthunderdetailDescription.text = "스크롤뷰 적용 후 스티링 더 길게 테스트 예정임다"
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

    // 팝업 메뉴 보여주는 메소드
    private fun showOptionPopup(v: View) {
//        val themeWrapper = ContextThemeWrapper(this, R.style.MyPopupMenu)
//        val popup = PopupMenu(themeWrapper, v, Gravity.END, 0, R.style.MyPopupMenu)
        val popup = PopupMenu(this, v)
        popup.menuInflater.inflate(R.menu.menu_popup, popup.menu)
        popup.setOnMenuItemClickListener {
            when (it?.itemId) {
                R.id.edit -> {
                    // TODO: 수정처리
                    Toast.makeText(this, "수정페이지로 넘어가보겠슴다~", Toast.LENGTH_LONG).show()
                    return@setOnMenuItemClickListener  true
                }
                R.id.delete -> {
                    //삭제 다이어로그 띄워주기
                    showDeleteDialog()
                    return@setOnMenuItemClickListener true
                }

            }
            return@setOnMenuItemClickListener false
        }
        popup.show()
    }

}

