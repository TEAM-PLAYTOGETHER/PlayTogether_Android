package com.playtogether_android.app.presentation.ui.main

import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityMainBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.HomeFragment
import com.playtogether_android.app.presentation.ui.main.viewmodel.MainViewModel
import com.playtogether_android.app.presentation.ui.message.MessageFragment
import com.playtogether_android.app.presentation.ui.mypage.MyPageFragment
import com.playtogether_android.app.presentation.ui.thunder.ThunderFragment
import com.playtogether_android.app.util.changeFragmentNoBackStack
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNav()

    }

    //바텀네비
    private fun initBottomNav() {

        //바텀 네비 아이템 클릭된 것 처럼 보이도록 ( 4-> 마이페이지, 2 -> 과방)
        // 첫 프래그먼트 설정 (닉네임 클릭시 마이페이지 및 선배 개인페이지를 위해)

        mainViewModel.bottomNavItem.observe(this) {
            when (it) {
                MYPAGE -> {
                    binding.btNvMain.selectedItemId = R.id.navigation_mypage
                    changeFragmentNoBackStack(
                        R.id.fragment_container_main,
                        MyPageFragment()
                    )
                }
                THUNDER -> {
                    binding.btNvMain.selectedItemId = R.id.navigation_thunder
                    changeFragmentNoBackStack(R.id.fragment_container_main, MessageFragment())

                }
                MESSAGE -> {
                binding.btNvMain.selectedItemId = R.id.navigation_message
                changeFragmentNoBackStack(R.id.fragment_container_main, MessageFragment())
            }

                else ->{
                    changeFragmentNoBackStack(R.id.fragment_container_main, HomeFragment())
                }
            }


            binding.btNvMain.itemIconTintList = null
            binding.btNvMain.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        changeFragmentNoBackStack(R.id.fragment_container_main, HomeFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.navigation_thunder -> {
                        changeFragmentNoBackStack(R.id.fragment_container_main, ThunderFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.navigation_message -> {
                        changeFragmentNoBackStack(
                            R.id.fragment_container_main,
                            MessageFragment()
                        )
                        return@setOnItemSelectedListener true
                    }
                    R.id.navigation_mypage -> {
                        changeFragmentNoBackStack(R.id.fragment_container_main, MyPageFragment())
                        return@setOnItemSelectedListener true
                    }
                }
                true
            }
        }
    }

    companion object {
        const val HOME = 1
        const val THUNDER = 2
        const val MESSAGE = 3
        const val MYPAGE = 4
    }
}


