package com.playtogether_android.app.presentation.ui.createThunder

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.thunder.GetThunderCreateData
import com.playtogether_android.domain.model.thunder.PostThunderCreateData
import com.playtogether_android.domain.usecase.thunder.PostThunderCreateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateThunderViewModel @Inject constructor(
    val postThunderCreateUseCase: PostThunderCreateUseCase
) : ViewModel() {
    private val _getThunderCreateData = MutableLiveData<GetThunderCreateData>()
    val getThunderCreateData: LiveData<GetThunderCreateData> get() = _getThunderCreateData
    val crewId = 56

    private var _photoList = MutableLiveData<MutableList<Uri>>()
    val photoList: LiveData<MutableList<Uri>> = _photoList

    fun setPhotoList(list: MutableList<Uri>) {
        _photoList.value = list
    }

    fun postThunderCreate(postThunderCreateData: PostThunderCreateData) {
        viewModelScope.launch {
            kotlin.runCatching { postThunderCreateUseCase(crewId, postThunderCreateData) }
                .onSuccess {
                    _getThunderCreateData.value = it
                    Log.d("writingServer", "글쓰기 뷰 성공")
                }
                .onFailure {
//                    _getThunderCreateData.value = GetThunderCreateData("false", -1, false)
                    it.printStackTrace()
                    Log.d("writingServer", "${it.message}")
                }
        }
    }
}