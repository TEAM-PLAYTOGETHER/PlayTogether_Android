package com.playtogether_android.app.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderAppliedBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.thunder.ApplicantListAdapter
import com.playtogether_android.app.presentation.ui.thunder.ApplyThunderDetailActivity
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderDetailViewModel
import androidx.activity.viewModels
import com.playtogether_android.app.presentation.ui.userInfo.OtherInfoActivity
import com.playtogether_android.app.presentation.ui.userInfo.viewmodel.UserInfoViewModel
import com.playtogether_android.data.singleton.PlayTogetherRepository
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class ThunderAppliedActivity :
    BaseActivity<ActivityThunderAppliedBinding>(R.layout.activity_thunder_applied) {

    private val thunderDetailViewModel: ThunderDetailViewModel by viewModels()
    private lateinit var adapter: ApplicantListAdapter
    private val userInfoViewModel: UserInfoViewModel by viewModels()

    // TODO: 이슈 번호 116
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        initData()
        initAdapter()
        setClickListener()
    }

    private fun initData() {
        thunderDetailViewModel.thunderDetail(getThunderId())
        thunderDetailViewModel.thunderDetailMember(getThunderId())
        thunderDetailViewModel.detailItemList.observe(this) {
            binding.detailData = it
            binding.tvAppliedMemberCnt.text =
                stringBuilder(
                    listOf(
                        "(", it.lightMemberCnt.toString(), "/",
                        it.peopleCnt.toString(), ")"
                    )
                )
        }
    }

    private fun stringBuilder(list: List<String>): String {
        val sb = StringBuilder()
        for (i in list) {
            if (i == "-1")
                getString(R.string.no_constraint)
            else
                sb.append(i)
        }
        return sb.toString()
    }

    private fun getThunderId(): Int = intent.getIntExtra("thunderId", -1)

    private fun setClickListener() {
        binding.ivThunderdetailBack.setOnClickListener {
            finish()
        }
        binding.tvThunderdetailDescription.setOnClickListener {
            val intent = Intent(this, ThunderDetailActivity::class.java)
            intent.putExtra("thunderId", getThunderId())
            startActivity(intent)
        }
    }


    //멤버들
    private fun initAdapter() {
        adapter = ApplicantListAdapter()
        binding.rvAppliedContainer.adapter = adapter
        thunderDetailViewModel.memberList.observe(this) {
            adapter.applicantList.addAll(it)
            adapter.notifyDataSetChanged()
        }

        adapter.itemClick = object : ApplicantListAdapter.ItemClick {
            override fun onClick(view: View, position: Int, userId: Int) {
                userInfoViewModel.getOtherInfo(PlayTogetherRepository.crewId, userId)
                if (PlayTogetherRepository.userUuid != userId) {
                    val intent = Intent(this@ThunderAppliedActivity, OtherInfoActivity::class.java)
                    intent.putExtra("memberId", userId)
                    startActivity(intent)
                }
            }
        }
    }
}