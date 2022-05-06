package com.playtogether_android.app.presentation.ui.sign.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playtogether_android.domain.model.sign.IdDuplicationCheckData
import com.playtogether_android.domain.model.sign.IdDuplicationCheckItem
import com.playtogether_android.domain.model.sign.SignUpData
import com.playtogether_android.domain.model.sign.SignUpItem
import com.playtogether_android.domain.usecase.sign.PostSignIdUseCase
import kotlinx.coroutines.launch

class SignViewModel(
    val postSignIdUseCase: PostSignIdUseCase,
    val postSignUpUseCase: PostSignIdUseCase
) : ViewModel() {
    var id = MutableLiveData<String>()
    var pw = MutableLiveData<String>()

    //아이디 중복 체크 변수
    private val _idDuplicationCheck = MutableLiveData<IdDuplicationCheckData>()
    val idDuplicationCheck: LiveData<IdDuplicationCheckData>
        get() = _idDuplicationCheck

    //회원가입
    private val signUp : MutableLiveData<SignUpItem> = MutableLiveData()



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
    fun postSignUp(signUpData: SignUpData) {
        viewModelScope.launch {
            kotlin.runCatching { postSignUp(signUpData) }
                .onSuccess {
                    //signUp.value = it
                    Log.d("SignUp", "서버 통신 성공")

                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("SignUp", "서버 통신 실패")
                }
       }
    }
}