package com.playtogether_android.app.presentation.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatCheckBox
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityLoginTermsBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.main.WebViewActivity
import com.playtogether_android.app.presentation.ui.onboarding.SelectOnboardingActivity
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginTermsActivity : BaseActivity<ActivityLoginTermsBinding>(R.layout.activity_login_terms) {
    private val signViewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        val list = listOf(
            binding.cbLogintermsTerm1,
            binding.cbLogintermsTerm2,
            binding.cbLogintermsTerm3,
            binding.cbLogintermsTerm4,
        )
        val textList =
            listOf(binding.tvLogintermsTerm2, binding.tvLogintermsTerm3, binding.tvLogintermsTerm4)
        initData()
        clickListener(list, textList)
    }

    private fun clickListener(list: List<AppCompatCheckBox>, textList: List<TextView>) {
        allPermsClickListener(list)
        termsChecker(list)
        termsTextClickListener(textList)
        btnClickListener()
        backBtnClickListener()
    }

    private fun backBtnClickListener() {
        binding.ivLogintermBack.setOnClickListener {
            finish()
        }
    }

    private fun termsTextClickListener(textList: List<TextView>) {
        for (item in textList) {
            item.setOnClickListener {
                Intent(this, WebViewActivity::class.java).apply {
                    putExtra("url", "www.naver.com")
                    startActivity(this)
                }
            }
        }
    }

    private fun btnClickListener() {
        binding.btnLogintermsAccess.setOnClickListener {
            //todo 약관은 저장해야 하나? 서버에 보내야 하나? 이부분은 어떻게 할지 같이 고민
            Intent(this, LoginInfoActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun initData() {
        binding.lifecycleOwner = this
    }

    private fun allPermsClickListener(list: List<AppCompatCheckBox>) {
        binding.cbLogintermsAll.apply {
            setOnClickListener {
                for (item in list) {
                    item.isChecked = this.isChecked
                }
                setButtonAccess(list)
            }
        }
    }

    private fun setButtonAccess(list: List<AppCompatCheckBox>) {
        var sum = 0
        for (i in 0..2) {
            if (list[i].isChecked) sum += 2
        }
        if (list[3].isChecked) sum += 1
        Timber.d("sum : $sum")

        if (sum == 7) {
            binding.cbLogintermsAll.isChecked = true
        }

        if (sum >= 6) {
            binding.btnLogintermsAccess.apply {
                isSelected = true
                isClickable = true
            }
        } else {
            binding.btnLogintermsAccess.apply {
                isSelected = false
                isClickable = false
                binding.cbLogintermsAll.isChecked = false
            }
        }
    }

    private fun termsChecker(list: List<AppCompatCheckBox>) {
        for (i in list.indices) {
            val item = list[i]
            item.setOnClickListener {
                setButtonAccess(list)
            }
        }
    }
}