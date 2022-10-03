package com.playtogether_android.app.presentation.ui.sign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.playtogether_android.app.BuildConfig
import com.playtogether_android.app.presentation.ui.login.viewmodel.GoogleLoginRepository
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.sign.SignInData
import com.playtogether_android.domain.model.sign.SignInItem
import com.playtogether_android.domain.model.sign.UserInfo
import com.playtogether_android.domain.repository.sign.SignRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
//    val postSignInUseCase: PostSignInUseCase,
    val repository: SignRepository
) : ViewModel() {

    var signData: MutableLiveData<String> = MutableLiveData()

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

    private val _statusCode = MutableLiveData<Int>()
    val statusCode: LiveData<Int> = _statusCode

    private var _signup = false
    val signup get() = _signup

    fun tokenChecker(refreshToken: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getTokenIssuance(refreshToken)
            }.onSuccess {
                _statusCode.value = it.status
                with(PlayTogetherRepository) {
                    userToken = it.accessToken
                    userRefreshToken = it.refreshToken
                }
            }.onFailure {
                Timber.e("token issuance : ${it.message}")
                when (it) {
                    is retrofit2.HttpException -> {
                        _statusCode.value = it.code()
                    }
                }
            }
        }
    }

    fun signup(body: UserInfo) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.putSignup(body)
            }.onSuccess {
                _isLogin.value = true
                Timber.e("signup-birth : ${body.birth}")
                Timber.e("signup-gender : ${body.gender}")
            }.onFailure {
                Timber.e("signup error : $it")
                _isLogin.value = false
            }
        }
    }

    fun googleLogin() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.postGoogleLogin()
            }.onSuccess {
                with(PlayTogetherRepository) {
                    googleUserToken = ""
                    googleUserToken = it.accessToken
                    userRefreshToken = it.refreshToken
                    userToken = it.accessToken
                    userLogin = true
                }
                _signup = it.isSignup
                _isLogin.value = true

            }.onFailure {
                Timber.e("google login error :${it.message}")
                _isLogin.value = false
            }
        }
    }

    fun kakaoLogin() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.postKakaoLogin()
            }.onSuccess {
                with(PlayTogetherRepository) {
                    //todo 인터셉트
                    kakaoUserToken = ""
                    kakaoUserToken = it.accessToken
                    userToken = kakaoUserToken
                    userRefreshToken = it.refreshToken
                    userLogin = true
                }
                Timber.e("kakao login access : ${it.accessToken}")
                Timber.e("kakao login refresh : ${it.refreshToken}")

                signData.value = it.accessToken //signData Set accessToken
                _signup = it.isSignup
                _isLogin.value = true

            }.onFailure {
                Timber.e("kakao login error :${it.message}")
                _isLogin.value = false
            }
        }
    }

    fun postSignIn(item: SignInItem) {

    }


}