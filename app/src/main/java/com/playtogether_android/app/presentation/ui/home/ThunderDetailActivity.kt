package com.playtogether_android.app.presentation.ui.home

import PhotoDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderDetailBinding
import com.playtogether_android.app.databinding.DialogCheckBinding
import com.playtogether_android.app.databinding.DialogYesNoBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.playtogether_android.app.presentation.ui.message.ChattingActivity
import com.playtogether_android.app.presentation.ui.mypage.OthersMyPageActivity
import com.playtogether_android.app.presentation.ui.thunder.ApplicantListAdapter
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderDetailViewModel
import com.playtogether_android.app.util.CustomDialog
import com.playtogether_android.app.util.CustomDialogSon
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.app.util.showCustomPopUp
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ThunderDetailActivity :
    BaseActivity<ActivityThunderDetailBinding>(R.layout.activity_thunder_detail) {
    private val thunderDetailViewModel: ThunderDetailViewModel by viewModels()
    private lateinit var applicantListAdapter: ApplicantListAdapter
    private val homeViewModel: HomeViewModel by viewModels()
    private var organizerId: Int = -1
    private var name: String = "null"
    private var roomId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val thunderId = intent.getIntExtra("thunderId", -1)
        initData(thunderId)
        initView()
        clickItems(thunderId)
    }

    private fun initView() {
        initAdapter()
        observeOrganizer()
        observeRoomId()
        checkCategory()
    }

    private fun clickItems(thunderId: Int) {
        clickScrap()
        clickOption(thunderId)
        clickReport()
        clickThunderCancel(thunderId)
        clickProfile()
        clickSendMessage()
        clickApply()
        clickBackArrow()
    }

    private fun clickThunderCancel(thunderId: Int) {
        binding.tvDetailCancelText.setOnClickListener {
            showCancelDialog2(thunderId)
        }
    }

    private fun showCancelDialog2(thunderId: Int) {
        val title = "신청을 취소할까요?"
        val dialog = CustomDialogSon(this)
        val db = DialogYesNoBinding.inflate(layoutInflater)
        dialog.setContentView(db.root)

        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        dialog.show()

        with(db) {
            tvDialogTitle.text = title
            tvDialogNo.setOnClickListener {
                dialog.dismiss()
            }
            tvDialogYes.setOnClickListener {
                with(thunderDetailViewModel) {
                    joinAndCancel(thunderId)
                    isConfirm.observe(this@ThunderDetailActivity) {
                        if (it) {
                            showConfirmDialog(dialog)
                        }
                    }
                }
                dialog.dismiss()
            }
        }
    }

//    private fun showCancelDialog(thunderId: Int) {
//        val title = "신청을 취소할까요?"
//        val dialog = CustomDialog(this, title)
//        dialog.showChoiceDialog(R.layout.dialog_yes_no)
//        dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
//            override fun onClicked(num: Int) {
//                if (num == 1) {
//                    thunderDetailViewModel.joinAndCancel(thunderId)
//                    thunderDetailViewModel.isConfirm.observe(this@ThunderDetailActivity) { success ->
//                        if (success) {
//                            showConfirmDialog()
//                        }
//                    }
//                }
//            }
//        })
//    }

    private fun showConfirmDialog(dialog: CustomDialogSon) {
        val title = "신청 취소되었습니다."
        val db = DialogCheckBinding.inflate(layoutInflater)
        dialog.setContentView(db.root)
        dialog.show()
        db.tvDialogTitle.text = title
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog.window?.setBackgroundDrawableResource(R.drawable.inset_horizontal_58)
        db.tvDialogCheck.setOnClickListener {
            dialog.dismiss()
            finish()
        }
    }

    private fun clickReport() {
        binding.tvThunderdetailReport.setOnClickListener {
            shortToast("신고뷰로 이동 예정")
        }
    }

    private fun clickOption(thunderId: Int) {
        with(binding.ivDetailOption) {
            setOnClickListener {
                Timber.e("option click")
                val popup = showCustomPopUp(it, R.array.option_popup, baseContext)
                popup.setOnItemClickListener { _, view, _, _ ->
                    if ((view as TextView).text == CHANGE) {
                        //todo 번개 생성뷰로 이동해야합니다 분기처리가 필요할듯
                        shortToast("구현중입니다 :)")
                        popup.dismiss()
                    } else {
                        with(thunderDetailViewModel) {
                            thunderDelete(thunderId)
                            isDelete.observe(this@ThunderDetailActivity) {
                                if (it) {
                                    popup.dismiss()
                                    finish()
                                    shortToast("삭제가 완료 되었습니다.")
                                }
                            }
                        }
                    }
                }
                popup.show()
            }
        }
    }

    private fun clickImage(image: String) {
        binding.ivDetailImage.setOnClickListener {
            val dialog = PhotoDialog(image)
            dialog.show(supportFragmentManager, "init photo fragment")
        }
    }

    private fun clickScrap() {
        val thunderId = intent.getIntExtra("thunderId", -1)
        binding.ivThunderdetailLike.setOnClickListener { view ->
            with(thunderDetailViewModel) {
                postScrap(thunderId)
                isLike.observe(this@ThunderDetailActivity) {
                    view.isSelected = it
                }
            }
        }
    }

    private fun initAdapter() {
        applicantListAdapter = ApplicantListAdapter()
        binding.rvThunderApplicantList.adapter = applicantListAdapter

        thunderDetailViewModel.memberList.observe(this) {
            applicantListAdapter.applicantList.addAll(it)
            applicantListAdapter.notifyDataSetChanged()
        }
    }

    private fun checkCategory() {
        val applyCategory = mutableListOf(
            binding.tvDetailCancelText,
            binding.clThunderdetailMessage,
            binding.clDetailBoundary,
            binding.clDetailOrganizerContainer,
            binding.tvThunderdetailReport,
            binding.clThunderApplicantContent
        )
        val openCategory = mutableListOf(
            binding.ivDetailOption,
            binding.clDetailOrganizerContainer,
            binding.clDetailBoundary,
            binding.clThunderApplicantContent
        )
        val defaultCategory = mutableListOf(
            binding.clThunderdetailMessage,
            binding.ivThunderdetailLike,
            binding.tvThunderdetailReport,
            binding.clThunderdetailApplyBtn
        )

        thunderDetailViewModel.isThunderType.observe(this) {
            if (it.isOrganizer) {
                binding.ivThunderdetailIcon.isClickable = false
                itemVisibility(openCategory)
            } else if (it.isEntered) {
                itemVisibility(applyCategory)
            } else {
                itemVisibility(defaultCategory)
            }
        }
    }

    private fun itemVisibility(list: MutableList<View>) {
        for (item in list) {
            item.visibility = View.VISIBLE
        }
    }

    private fun clickApply() {
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
                    val thunderId = intent.getIntExtra("thunderId", -1)
                    homeViewModel.postJoinThunder(thunderId)
                    homeViewModel.joinThunder.observe(this@ThunderDetailActivity) {
                        if (it.success) {
                            val intent =
                                Intent(
                                    this@ThunderDetailActivity, ThunderAppliedActivity::class.java
                                )
                            intent.putExtra("thunderId", thunderId)
                            startActivity(intent)
                            this@ThunderDetailActivity.finish()
                        } else {
                            Log.d("번개참여", "실패")
                        }
                    }
                }
            }
        })
    }

    private fun initData(thunderId: Int) {
        binding.lifecycleOwner = this
        binding.viewModel = thunderDetailViewModel

        with(thunderDetailViewModel) {
            thunderDetail(thunderId)
            thunderDetailMember(thunderId)
            thunderDetailOrganizer(thunderId)
            getThunderInfo(thunderId)
        }
        thunderDetailViewModel.detailItemList.observe(this) {
            binding.detailData = it
            val image = it.image
            if (image.isEmpty()) {
                binding.ivDetailImage.visibility = View.GONE
            } else {
                clickImage(it.image)
                Glide
                    .with(this)
                    .load(it.image)
                    .into(binding.ivDetailImage)
            }
        }

        thunderDetailViewModel.organizerInfo.observe(this) {
            binding.organizer = it
        }
        thunderDetailViewModel.getScrapValue(thunderId)
    }

    private fun observeOrganizer() {
        thunderDetailViewModel.organizerInfo.observe(this) {
            organizerId = it.organizerId
            name = it.name
            thunderDetailViewModel.getRoomId(organizerId)
        }
    }

    private fun observeRoomId() {
        thunderDetailViewModel.roomId.observe(this) {
            roomId = it
        }
    }

    private fun clickSendMessage() {
        binding.clThunderdetailMessage.setOnClickListener {
            val intent = Intent(this, ChattingActivity::class.java)
            intent.putExtra("audienceId", organizerId)
            intent.putExtra("roomId", roomId)
            intent.putExtra("name", name)
            if (roomId != -1) startActivity(intent)
            else shortToast("실패했습니다")
        }
    }

    private fun clickBackArrow() {
        binding.ivThunderdetailBack.setOnClickListener {
            finish()
        }
    }

    private fun clickProfile() {
        // TODO: 혜빈아 요기!!!!!!!!! 일단 코드가 지저분하지만.. 나중에 정리할게ㅋㅋㅋ
        // 개설자 프로필로 이동
        binding.ivThunderdetailIcon.setOnClickListener {
            var organizerName: String? = null
            var organizerId: Int? = null
            thunderDetailViewModel.organizerInfo.observe(this) {
                organizerId = it.organizerId
                organizerName = it.name
            }

            val intent = Intent(this, OthersMyPageActivity::class.java)
            intent.putExtra("organizerId", organizerId)
            intent.putExtra("organizerName", organizerName)

            startActivity(intent)
        }
    }

    companion object {
        const val APPLY = "apply"
        const val OPEN = "open"
        const val LIKE = "like"
        const val DEFAULT = "default"
        const val CHANGE = "수정"
        const val DELETE = "삭제"
    }
}