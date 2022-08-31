package com.playtogether_android.app.presentation.ui.sign.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.sign.*
import com.playtogether_android.domain.repository.sign.SignRepository
import com.playtogether_android.domain.usecase.sign.PostSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
//    val postSignInUseCase: PostSignInUseCase,
    val repository: SignRepository
) : ViewModel() {

    //로그인 시 필요한 값
    var id = MutableLiveData<String>()
    var pw = MutableLiveData<String>()


    //로그인 request
    var requestSignIn = SignInItem("", "")

    var name = MutableLiveData<String>()


    //로그인
    private var _signIn = MutableLiveData<SignInData>()
    val signIn: LiveData<SignInData>
        get() = _signIn

    //jwt토큰 set
    var signInToken: MutableLiveData<SignInData> = MutableLiveData()

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> = _isLogin

    fun kakaoLogin(): Boolean {
        var isSignup = false
        viewModelScope.launch {
            kotlin.runCatching {
                repository.postKakaoLogin()
            }.onSuccess {
                with(PlayTogetherRepository) {
                    kakaoAccessToken = "" // todo 인터셉트 변경 위함
                    kakaoUserToken = it.accessToken
                    kakaoAccessToken = it.accessToken
                    userToken = kakaoUserToken
                    kakaoUserRefreshToken = it.refreshToken
                    kakaoUserlogOut = false
                }
                isSignup = it.isSignup
                _isLogin.value = true
            }.onFailure {
                Timber.e("kakao login error :${it.message}")
                _isLogin.value = false
            }
        }
        return isSignup
    }

    fun postSignIn(item: SignInItem) {

    }


}