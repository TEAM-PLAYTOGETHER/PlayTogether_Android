package com.playtogether_android.app.presentation.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    private val _selectedTags = MutableLiveData<List<String>>(mutableListOf())
    var selectedTags = _selectedTags
}