package com.playtogether_android.app.presentation.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    //바텀 네비 아이템들 클릭된
    var bottomNavItem = MutableLiveData<Int>(0)


}