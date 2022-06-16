package com.playtogether_android.app.presentation.ui.sign.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.sign.*
import com.playtogether_android.domain.usecase.sign.PostSignIdUseCase
import com.playtogether_android.domain.usecase.sign.PostSignInUseCase
import com.playtogether_android.domain.usecase.sign.PostSignUpUseCaes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
    val postSignIdUseCase: PostSignIdUseCase,
    val postSignUpUseCase: PostSignUpUseCaes,
    val postSignInUseCase: PostSignInUseCase
) : ViewModel() {

    //로그인 시 필요한 값
    var id = MutableLiveData<String>()
    var pw = MutableLiveData<String>()

    //회원가입 request
    var requestSignUp = SignUpItem("", "", "", "", "", "")

    //로그인 request
    var requestSignIn = SignInItem("", "")

    var name = MutableLiveData<String>()


    //아이디 중복 체크 변수
    private val _idDuplicationCheck = MutableLiveData<IdDuplicationCheckData>()
    val idDuplicationCheck: LiveData<IdDuplicationCheckData>
        get() = _idDuplicationCheck

    //회원가입
    private val _signUp = MutableLiveData<SignUpData>()
    val signUp: LiveData<SignUpData>
        get() = _signUp

    //로그인
    private var _signIn = MutableLiveData<SignInData>()
    val signIn: LiveData<SignInData>
        get() = _signIn

    //jwt토큰 set
    var signInToken: MutableLiveData<SignInData> = MutableLiveData()

    //아이디 중복 체크
    fun postIdDuplication(idDuplicationCheckItem: IdDuplicationCheckItem) {
        viewModelScope.launch {
            kotlin.runCatching { postSignIdUseCase(idDuplicationCheckItem) }
                .onSuccess {
                    _idDuplicationCheck.value = it
                    Log.d("test", ":" + _idDuplicationCheck.value)
                    Log.d("IdDuplication", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("IdDuplication", "서버 통신 실패")
                }
        }
    }

    //회원가입
    fun postSignUp(signUpItem: SignUpItem) {
        viewModelScope.launch {
            kotlin.runCatching { postSignUpUseCase(signUpItem) }
                .onSuccess {
                    _signUp.value = it
                    Log.d("SignUp", "서버 통신 성공")

                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("SignUp", "서버 통신 실패")
                }
        }
    }

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
}