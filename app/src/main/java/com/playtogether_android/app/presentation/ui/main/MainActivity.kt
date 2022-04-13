package com.playtogether_android.app.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityMainBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.HomeFragment
import com.playtogether_android.app.presentation.ui.main.viewmodel.MainViewModel
import com.playtogether_android.app.presentation.ui.message.MessageFragment
import com.playtogether_android.app.presentation.ui.mypage.MyPageFragment
import com.playtogether_android.app.presentation.ui.thunder.ThunderFragment
import com.playtogether_android.app.util.changeFragment
import com.playtogether_android.app.util.changeFragmentNoBackStack
import com.playtogether_android.app.util.popFragmentBackStack
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeFragmentNoBackStack(R.id.fragment_container_main, HomeFragment())
        initBottomNav()
    }


    private var prevSelectedItem: Int = 1


    //바텀네비
    private fun initBottomNav() {
        binding.btNvMain.itemIconTintList = null
        binding.btNvMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    prevSelectedItem = 1
                    supportFragmentManager.popBackStack()
                    changeFragmentNoBackStack(R.id.fragment_container_main, HomeFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_thunder -> {
                    if (prevSelectedItem == 1) {
                        changeFragment(R.id.fragment_container_main, ThunderFragment(), "Thunder")
                    } else {
                        changeFragmentNoBackStack(R.id.fragment_container_main, ThunderFragment())
                    }

                    prevSelectedItem = 2
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_message -> {
                    if (prevSelectedItem == 1) {
                        changeFragment(R.id.fragment_container_main, MessageFragment(), "Message")
                    } else {
                        changeFragmentNoBackStack(R.id.fragment_container_main, MessageFragment())
                    }

                    prevSelectedItem = 3
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_mypage -> {
                    if (prevSelectedItem == 1) {
                        changeFragment(R.id.fragment_container_main, MyPageFragment(), "MyPage")
                    } else {
                        changeFragmentNoBackStack(R.id.fragment_container_main, MyPageFragment())
                    }

                    prevSelectedItem = 4
                    return@setOnItemSelectedListener true
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(prevSelectedItem != 1) {
            binding.btNvMain.selectedItemId = R.id.navigation_home
        }
    }
}




