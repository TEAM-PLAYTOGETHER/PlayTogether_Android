package com.playtogether_android.app.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityThunderAppliedBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.thunder.ApplicantListAdapter
import com.playtogether_android.app.presentation.ui.thunder.ApplyThunderDetailActivity
import com.playtogether_android.app.presentation.ui.thunder.ThunderListAdapter
import com.playtogether_android.app.presentation.ui.thunder.list.adapter.ThunderCategoryListAdapter
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderDetailViewModel
import com.playtogether_android.app.util.shortToast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class ThunderAppliedActivity :
    BaseActivity<ActivityThunderAppliedBinding>(R.layout.activity_thunder_applied) {

    private val thunderDetailViewModel: ThunderDetailViewModel by viewModels()
    private lateinit var adapter: ApplicantListAdapter

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
                getString(R.string.createthunder_infinite)
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
            val intent = Intent(this, ApplyThunderDetailActivity::class.java)
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
    }
}