package com.playtogether_android.app.presentation.ui.thunder

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.bumptech.glide.Glide
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOpenThunderDetailBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderDetailViewModel
import com.playtogether_android.app.util.CustomDialog
import com.playtogether_android.app.util.imageNullCheck
import com.playtogether_android.app.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels

@AndroidEntryPoint
class OpenThunderDetailActivity :
    BaseActivity<ActivityOpenThunderDetailBinding>(R.layout.activity_open_thunder_detail) {

    private val thunderDetailViewModel: ThunderDetailViewModel by viewModels()
    private lateinit var applicantListAdapter: ApplicantListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val thunderId = intent.getIntExtra("thunderId", -1)
        initData(thunderId)
        initAdapter()
        setClickListener()

        binding.ivOpenthunderdetailOption.setOnClickListener {
            showOptionPopup(binding.ivOpenthunderdetailOption)
        }
    }

    private fun initData(thunderId: Int) {

        with(thunderDetailViewModel) {
            thunderDetail(thunderId)
            thunderDetailMember(thunderId)
            thunderDetailOrganizer(thunderId)
        }

        thunderDetailViewModel.detailItemList.observe(this) {
            binding.detailData = it
            imageNullCheck(it.image, binding.ivOpenthunderdetailImage)

//            Glide
//                .with(this)
//                .load(it.image)
//                .into(binding.ivOpenthunderdetailImage)
        }

        thunderDetailViewModel.organizerInfo.observe(this) {
            binding.organizer = it
        }
    }

    private fun setClickListener() {

        // 뒤로가기 버튼
        binding.ivOpenthunderdetailBack.setOnClickListener {
            finish()
        }
    }

    private fun showThunderDeleteDialog(thunderId: Int) {
        val title = "게시글을 삭제할까요?"
        val dialog = CustomDialog(this, title)
        dialog.showChoiceDialog(R.layout.dialog_yes_no)
        dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
            override fun onClicked(num: Int) {
                if (num == 1) {
                    thunderDetailViewModel.thunderDelete(thunderId)
                    thunderDetailViewModel.isDelete.observe(this@OpenThunderDetailActivity) { success ->
                        if (success) {
                            showConfirmDialog()
                        }
                    }
                }
            }
        })
    }

    private fun showConfirmDialog() {
        val title = "게시글이 삭제되었습니다."
        val dialog = CustomDialog(this@OpenThunderDetailActivity, title)
        dialog.showDeleteDialog(R.layout.dialog_check)
    }


    private fun initAdapter() {
        applicantListAdapter = ApplicantListAdapter()
        binding.rvThunderApplicantList.adapter = applicantListAdapter

        thunderDetailViewModel.memberList.observe(this) {
            applicantListAdapter.applicantList.addAll(it)
            applicantListAdapter.notifyDataSetChanged()
        }

    }

    // 팝업 메뉴 보여주는 메소드
    private fun showOptionPopup(v: View) {
//        val themeWrapper = ContextThemeWrapper(this, R.style.MyPopupMenu)
//        val popup = PopupMenu(themeWrapper, v, Gravity.END, 0, R.style.MyPopupMenu)
        val thunderId = intent.getIntExtra("thunderId", -1)
        val popup = PopupMenu(this, v)
        popup.menuInflater.inflate(R.menu.menu_popup, popup.menu)
        popup.setOnMenuItemClickListener {
            when (it?.itemId) {
                R.id.edit -> {
                    // TODO: 수정처리
                    Toast.makeText(this, "수정페이지로 넘어가보겠슴다~", Toast.LENGTH_LONG).show()
                    return@setOnMenuItemClickListener true
                }
                R.id.delete -> {
                    //삭제 다이어로그 띄워주기
                    // 여기 코드 나중에 꼭 수정ㅋㅋㅋ
                    showThunderDeleteDialog(thunderId)
                    return@setOnMenuItemClickListener true
                }

            }
            return@setOnMenuItemClickListener false
        }
        popup.show()
    }

}

