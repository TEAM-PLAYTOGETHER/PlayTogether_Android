package com.playtogether_android.app.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderDetailBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.util.CustomDialog

class ThunderDetailActivity : BaseActivity<ActivityThunderDetailBinding>(R.layout.activity_thunder_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        binding.clThunderdetailApplyBtn.setOnClickListener {
            showApplyDialog()
        }
    }

    private fun showApplyDialog(){
        val title = "번개에 참여할까요?"
        val dialog=CustomDialog(this, title)
        dialog.showChoiceDialog(R.layout.dialog_yes_no)
        dialog.setOnClickedListener(object:CustomDialog.ButtonClickListener{
            override fun onClicked(num: Int) {
                if(num==1){
                    val intent = Intent(this@ThunderDetailActivity, ThunderAppliedActivity::class.java)
                    startActivity(intent)
                    this@ThunderDetailActivity.finish()
                }
            }
        })
    }

    private fun initData(){
        with(binding){
            tvThunderdetailCurrent.text="1"
            tvThunderdetailMax.text="6"
            tvThunderdetailName.text="문수제비"
            tvThunderdetailTitle.text="우리집에서 피자 먹기"
            tvThunderdetailDateContent.text="2022.04.15"
            tvThunderdetailTimeContent.text="18:00 ~"
            tvThunderdetailPlaceContent.text="우리집"
            tvThunderdetailCategoryContent.text="음식"
            tvThunderdetailDescription.text=resources.getString(R.string.thunderdetail_example)
        }

    }
}