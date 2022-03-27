package com.playtogether_android.app.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityTestBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class TestActivity : BaseActivity<ActivityTestBinding>(R.layout.activity_test) {

    private val testViewModel : TestViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTestData()
    }


    private fun initTestData() {
        testViewModel.getTestData()
        testViewModel.testInfo.observe(this) {
            binding.testData = it[0]
        }
    }
}