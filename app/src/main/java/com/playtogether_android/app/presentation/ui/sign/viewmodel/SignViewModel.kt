package com.playtogether_android.app.presentation.ui.sign.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.sign.*
import com.playtogether_android.domain.usecase.sign.PostSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
    val postSignInUseCase: PostSignInUseCase
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


    //로그인
    fun postSignIn(signInItem: SignInItem) {
        viewModelScope.launch {
            kotlin.runCatching { postSignInUseCase(signInItem) }
                .onSuccess {
                    _signIn.value = it
                    Log.d("SignIn", "서버 통신 성공")
                    Log.d("SignName", "" + signIn.value!!.userName)
                }
                .onFailure {
                    it.printStackTrace()
                    _signIn.value = SignInData(false, "", "", "")
                    Log.d("SignIn", "서버 통신 실패")
                }
        }
    }

    fun kakaoLogin() {
        viewModelScope.launch {
            kotlin.runCatching {

            }
        }
    }


}