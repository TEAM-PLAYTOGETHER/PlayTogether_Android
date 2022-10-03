package com.playtogether_android.app.presentation.ui.thunder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.GenericData
import com.playtogether_android.domain.model.light.CategoryData
import com.playtogether_android.domain.model.thunder.ReportData
import com.playtogether_android.domain.model.thunder.ThunderTabListData
import com.playtogether_android.domain.repository.thunder.ThunderRepository
import com.playtogether_android.domain.usecase.thunder.GetApplyListUseCase
import com.playtogether_android.domain.usecase.thunder.GetLikeListUseCase
import com.playtogether_android.domain.usecase.thunder.GetOpenListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ThunderViewModel @Inject constructor(
    val getApplyListUseCase: GetApplyListUseCase,
    val getOpenListUseCase: GetOpenListUseCase,
    val getLikeListUseCase: GetLikeListUseCase,
    val thunderRepository: ThunderRepository
) : ViewModel() {

    private val _thunderTabListData = MutableLiveData<ThunderTabListData>()
    val thundertabListData: LiveData<ThunderTabListData>
        get() = _thunderTabListData

    private val _thunderApplyList = MutableLiveData<List<CategoryData>>()
    val thunderApplyList: LiveData<List<CategoryData>> = _thunderApplyList

    private var _thunderApplyIdList = listOf<Int>()
    val thunderApplyIdList get() = _thunderApplyIdList

    private val _thunderOpenList = MutableLiveData<List<CategoryData>>()
    val thunderOpenList: LiveData<List<CategoryData>> = _thunderOpenList

    private var _thunderOpenIdList = listOf<Int>()
    val thunderOpenIdList get() = _thunderOpenIdList

    private val _thunderLikeList = MutableLiveData<List<CategoryData>>()
    val thunderLikeList: LiveData<List<CategoryData>> = _thunderLikeList

    private val _reportPost = MutableLiveData<GenericData>()
    val reportPost: LiveData<GenericData>
        get() = _reportPost

    // 번개 게시글 신고 reqest
    var requestReport = ReportData("")


    //번개탭-신청한 번개 리스트
    fun getApplyList() {
        viewModelScope.launch {
            kotlin.runCatching { getApplyListUseCase() }
                .onSuccess {
                    _thunderApplyList.value = it
                    _thunderApplyIdList = it.map { it.lightId }
                    Timber.e("thunder apply : $_thunderApplyIdList")
                    Timber.d("thunder apply size : ${it.size}")
                }
                .onFailure {
                    it.printStackTrace()
                    Timber.d("getApplyList-fail: fail")
                }
        }
    }
    fun getOpenList() {
        viewModelScope.launch {
            kotlin.runCatching {
                getOpenListUseCase()
            }.onSuccess {
                _thunderOpenList.value = it
                _thunderOpenIdList = it.map { it.lightId }
                Timber.e("thunder open : $_thunderOpenIdList")
            }.onFailure {

            }
        }
    }

    fun getLikeList() {
        viewModelScope.launch {
            kotlin.runCatching {
                getLikeListUseCase()
            }.onSuccess {
                _thunderLikeList.value = it
                Timber.d("thunder like : $it")
            }.onFailure {

            }
        }
    }

    //번개탭-오픈한 번개 리스트
//    fun getOpenList() = viewModelScope.launch {
//        kotlin.runCatching { getOpenListUseCase() }
//            .onSuccess {
//                _thunderTabListData.postValue(it)
//                Log.d("getOpenList", it.toString())
//            }
//            .onFailure {
//                it.printStackTrace()
//                Log.d("getOpenList-fail", "fail")
//            }
//    }

    //번개탭-찜한 번개 리스트
//    fun getLikeList() = viewModelScope.launch {
//        kotlin.runCatching { getLikeListUseCase() }
//            .onSuccess {
//                _thunderTabListData.postValue(it)
//                Log.d("getLikeList", it.toString())
//            }
//            .onFailure {
//                it.printStackTrace()
//                Log.d("getOpenList-fail", "fail")
//            }
//    }

    // 번개 게시글 신고
    fun postReport(thunderId: Int, reportData: ReportData) {
        viewModelScope.launch {
            kotlin.runCatching { thunderRepository.postReport(thunderId, reportData) }
                .onSuccess {
                    _reportPost.value = it
                    Timber.d("postReport-server 성공: $it")
                }
                .onFailure {
                    it.printStackTrace()
                    Timber.d("postReport-server 실패: $it")
                }

        }
    }


}