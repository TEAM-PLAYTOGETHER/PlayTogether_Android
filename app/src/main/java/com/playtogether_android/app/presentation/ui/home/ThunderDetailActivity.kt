package com.playtogether_android.app.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderDetailBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.message.ChattingActivity
import com.playtogether_android.app.presentation.ui.mypage.MyPageFragment
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderDetailViewModel
import com.playtogether_android.app.util.CustomDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThunderDetailActivity :
    BaseActivity<ActivityThunderDetailBinding>(R.layout.activity_thunder_detail) {
    private val thunderDetailViewModel: ThunderDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        setClickListener()
        binding.clThunderdetailApplyBtn.setOnClickListener {
            showApplyDialog()
        }
    }

    private fun showApplyDialog() {
        val title = "번개에 참여할까요?"
        val dialog = CustomDialog(this, title)
        dialog.showChoiceDialog(R.layout.dialog_yes_no)
        dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
            override fun onClicked(num: Int) {
                if (num == 1) {
                    val intent =
                        Intent(this@ThunderDetailActivity, ThunderAppliedActivity::class.java)
                    startActivity(intent)
                    this@ThunderDetailActivity.finish()
                }
            }
        })
    }

    private fun initData() {
        val thunderId = intent.getIntExtra("thunderId", -1)
        with(thunderDetailViewModel) {
            thunderDetail(thunderId)
            thunderDetailMember(thunderId)
            thunderDetailOrganizer(thunderId)
        }
        thunderDetailViewModel.detailItemList.observe(this) {
            binding.detailData = it
//            Glide
//                .with(this)
//                .load(it.image)
//                .into(binding.ivThunderdetailIcon) 여따가 이미지 넣으면 됨
        }

        thunderDetailViewModel.organizerInfo.observe(this) {
            binding.organizer = it
        }
//        with(binding){
//            tvThunderdetailCurrent.text="1"
//            tvThunderdetailMax.text="6"
//            tvThunderdetailName.text="문수제비"
//            tvThunderdetailTitle.text="우리집에서 피자 먹기"
//            tvThunderdetailDateContent.text="2022.04.15"
//            tvThunderdetailTimeContent.text="18:00 ~"
//            tvThunderdetailPlaceContent.text="우리집"
//            tvThunderdetailCategoryContent.text="음식"
//            tvThunderdetailDescription.text=resources.getString(R.string.thunderdetail_example)
//        }

    }

    private fun setClickListener() {

        binding.clThunderdetailMessage.setOnClickListener {
//           쪽지 보내기로 이동
            var organizerId = -1
            thunderDetailViewModel.organizerInfo.observe(this) {
                organizerId = it.organizerId
            }
            val intent = Intent(this, ChattingActivity::class.java)
            intent.putExtra("organizerId", organizerId)
            startActivity(intent)
        }

        // 뒤로가기 버튼
        binding.ivThunderdetailBack.setOnClickListener {
            finish()
        }


        // TODO: 혜빈아 요기!!!!!!!!! 일단 코드가 지저분하지만.. 나중에 정리할게ㅋㅋㅋ
        // 개설자 프로필로 이동
        binding.ivThunderdetailIcon.setOnClickListener {
            var userLoginId: String? = null
            thunderDetailViewModel.organizerInfo.observe(this) {
                userLoginId = it.userLoginId.toString()
            }
            var intent = Intent(this, MyPageFragment::class.java)
            intent.putExtra("userLoginId", userLoginId)
            startActivity(intent)
        }

    }

}