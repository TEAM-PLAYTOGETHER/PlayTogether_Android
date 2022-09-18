package com.playtogether_android.app.presentation.ui.userInfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityOtherInfoBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.main.WebViewActivity
import com.playtogether_android.app.presentation.ui.message.ChattingActivity
import com.playtogether_android.app.presentation.ui.userInfo.viewmodel.UserInfoViewModel
import com.playtogether_android.app.util.DateTimeUtil
import com.playtogether_android.app.util.showCustomPopUp
import com.playtogether_android.data.singleton.PlayTogetherRepository
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class OtherInfoActivity : BaseActivity<ActivityOtherInfoBinding>(R.layout.activity_other_info) {

    private val userInfoViewModel: UserInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val memberId = intent.getIntExtra("memberId", -1)
        val crewId = PlayTogetherRepository.crewId
        clickEvent()
        getOtherInfo(crewId, memberId)
        initData()
        Timber.d("initOtherInfo : $crewId, $memberId")
    }

    private fun clickEvent() {
        moveChattingView()
        btnBackEvent()
        btnOption()
    }

    // 채팅뷰로 이동
    private fun moveChattingView() {
        binding.clOtherInfoBtnChatting.setOnClickListener {
            startActivity(Intent(this, ChattingActivity::class.java))
        }
    }

    // 뒤로가기 버튼
    private fun btnBackEvent() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun initIntent(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }

    // 차단/신고 옵션 버튼
    //todo 옵션 차단/신고 기능
    private fun btnOption() {
        binding.btnOption.setOnClickListener {
            val popup = showCustomPopUp(it, R.array.option_mypage, baseContext)
            popup.setOnItemClickListener { _, view, _, _ ->
                if ((view as TextView).text == "차단") {
                    //todo 차단 API 연결
                    popup.dismiss()
                } else {
                    initIntent("https://cheddar-liquid-051.notion.site/14fc6c632471488486e7e76bc161069e")
                    popup.dismiss()

                }
            }
            popup.show()
        }
    }

    private fun getOtherInfo(crewId: Int, memberId: Int) {
        userInfoViewModel.getOtherInfo(crewId, memberId)
    }

    private fun initData() {
        userInfoViewModel.otherInfoData.observe(this) {
            val birth = DateTimeUtil.convertBirthFormat(it.birth)
            val gender = it.gender
            val genderFormat: String
            if (gender == "남") genderFormat = "M"
            else genderFormat = "W"

            binding.birthAndGender = "${birth}년생 ・ $genderFormat"
            binding.otherInfo = it
            binding.tvOtherInfoTitle.text = "${it.nickname}님의 프로필"
            binding.tvOtherInfoChatting.text = "${it.nickname}님과 채팅하기"
        }
    }
}