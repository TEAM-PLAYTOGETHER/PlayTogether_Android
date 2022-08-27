package com.playtogether_android.app.presentation.ui.createThunder

import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.playtogether_android.data.model.request.thunder.RequestThunderCreate
import com.playtogether_android.domain.model.thunder.GetThunderCreateData
import com.playtogether_android.domain.model.thunder.PostThunderCreateData
import com.playtogether_android.domain.repository.thunder.ThunderCreateRepository
import com.playtogether_android.domain.usecase.thunder.PostThunderCreateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CreateThunderViewModel @Inject constructor(
    val postThunderCreateUseCase: PostThunderCreateUseCase,
    val repository: ThunderCreateRepository
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