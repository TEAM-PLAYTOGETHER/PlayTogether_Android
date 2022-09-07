package com.playtogether_android.app.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityMainBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.view.HomeFragment
import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.playtogether_android.app.presentation.ui.message.MessageFragment
import com.playtogether_android.app.presentation.ui.mypage.MyPageFragment
import com.playtogether_android.app.presentation.ui.thunder.ThunderFragment
import com.playtogether_android.app.presentation.ui.thunder.viewmodel.ThunderViewModel
import com.playtogether_android.app.presentation.ui.userInfo.MyInfoFragment
import com.playtogether_android.app.util.PlayTogetherSharedPreference
import com.playtogether_android.app.util.changeFragment
import com.playtogether_android.app.util.changeFragmentNoBackStack
import com.playtogether_android.data.singleton.PlayTogetherRepository
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val homeViewModel: HomeViewModel by viewModels()
    private val thunderViewModel: ThunderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeFragmentNoBackStack(R.id.fragment_container_main, HomeFragment())
        initBottomNav()
        FirebaseApp.initializeApp(this)
//        val token = FirebaseMessaging.getInstance().token
        getFCMToken()
//        Timber.e("firebase token :$token")
        initData()
    }

    private fun initData() {
        Timber.d("JWT 토큰 : ${PlayTogetherRepository.userToken}")
    }


    private var prevSelectedItem: Int = 1


    private fun getFCMToken(): String? {
        var token: String? = null
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
//                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            token = task.result
            // Log and toast
            Timber.e("FCM Token is ${token}")
        })

        return token
    }

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
                    with(thunderViewModel) {
                        getApplyList()
                        getOpenList()
                        getLikeList()
                    }
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
                        changeFragment(R.id.fragment_container_main, MyInfoFragment(), "MyPage")
                    } else {
                        changeFragmentNoBackStack(R.id.fragment_container_main, MyInfoFragment())
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
        if (prevSelectedItem != 1) {
            binding.btNvMain.selectedItemId = R.id.navigation_home
        }
    }
}




