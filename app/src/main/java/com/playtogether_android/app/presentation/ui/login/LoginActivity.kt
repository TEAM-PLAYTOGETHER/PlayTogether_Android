package com.playtogether_android.app.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.playtogether_android.app.R
import com.playtogether_android.app.databinding.ActivityLoginBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import com.playtogether_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.playtogether_android.app.presentation.ui.login.view.LoginTermsActivity
import com.playtogether_android.app.presentation.ui.login.viewmodel.GoogleLoginRepository
import com.playtogether_android.app.presentation.ui.main.MainActivity
import com.playtogether_android.app.presentation.ui.onboarding.OnboardingReDownLoadActivity
import com.playtogether_android.app.presentation.ui.onboarding.SelectOnboardingActivity
import com.playtogether_android.app.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import com.playtogether_android.app.presentation.ui.sign.viewmodel.SignViewModel
import com.playtogether_android.app.util.PlayTogetherSharedPreference
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.data.singleton.PlayTogetherRepository
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val signViewModel: SignViewModel by viewModels()
    private val onBoardingViewModel: OnBoardingViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var startForActivity: ActivityResultLauncher<Intent>
    private lateinit var client: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        var keyHash = Utility.getKeyHash(this)
        Timber.e("kkkkkkkkkk: $keyHash")
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initView() {
        googleLoginCallback()
        onClickListener()
    }

    private fun initData() {
        homeViewModel.getCrewList()
    }

    private fun googleLoginCallback() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope(Scopes.DRIVE_APPFOLDER))
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestServerAuthCode(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        client = GoogleSignIn.getClient(this, gso)

        startForActivity =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                    handleSignInResult(task)
                } else {
                    Timber.e("result : $it")
                    Timber.e("result code: ${it.resultCode}")
                }
            }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
//            val clientId = BuildConfig.GOOGLE_CLIENT_ID
//            val clientSecret = BuildConfig.GOOGLE_CLIENT_SECRET
//
//            GoogleLoginRepository(clientId, clientSecret)
//                .getAccessToken(account.serverAuthCode!!)
            with(signViewModel) {
                googleLogin()
                isLogin.observe(this@LoginActivity) { success ->
                    if (success) {
                        Timber.e("login : 구글 로그인 성공")
                        signupChecker()
                    }
                }
            }

        } catch (e: ApiException) {
            Timber.e("signInResult:failed code=" + e.statusCode)
            shortToast("로그인 실패")
        }
    }

    private fun onClickListener() {
        btnKakaoListener()
        btnGoogleListener()
    }

    private fun btnKakaoListener() {
        binding.ivLoginKakao.setOnClickListener {
            setKakaoBtnListener()
        }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
//        updateUI(account)
    }

    private fun btnGoogleListener() {
        binding.ivLoginGoogle.setOnClickListener {
            val intent = client.signInIntent
            startForActivity.launch(intent)
        }
    }

    private fun nextActivity(intent: Intent) {
        startActivity(intent)
    }

    private fun signupChecker() {
        val isListEmpty = homeViewModel.isCrewListEmpty
        val crewId = PlayTogetherRepository.crewId
        Timber.e("list empty : $isListEmpty")
        if (signViewModel.signup) {
            //TODO: 회원가입했고 현재 등록된 crewId가 있음
            if (crewId != -1) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                nextActivity(intent)
            }
            // TODO: 회원이지만 재설치를 해서 preference가 비어있음 -> 가입한 동아리 선택뷰로 이동
            else if (crewId == -1 && !isListEmpty) {
                val intent =
                    Intent(this@LoginActivity, OnboardingReDownLoadActivity::class.java)
                nextActivity(intent)
            }
            // TODO: 회원이지만 가입한 동아리가 없는 경우
            else if (crewId == -1 && isListEmpty) {
                val intent = Intent(this@LoginActivity, SelectOnboardingActivity::class.java)
                nextActivity(intent)
            }
        } else {
            //todo 회원가입안함 -> 약관 뷰 이동
            val intent =
                Intent(this@LoginActivity, LoginTermsActivity::class.java)
            nextActivity(intent)
        }
    }

    private fun setKakaoBtnListener() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                shortToast("로그인 실패")
                getErrorLog(error)
            } else if (token != null) {
                UserApiClient.instance.me { _, error ->
                    PlayTogetherRepository.kakaoAccessToken = token.accessToken
                    Timber.e("kakao access token : ${token.accessToken}")
                    with(signViewModel) {
                        kakaoLogin()
                        isLogin.observe(this@LoginActivity) { success ->
                            if (success) {
                                signupChecker()
                            }
                        }
                    }
                }
            } else {
                shortToast("else")
                Timber.e("모르겠다.")
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
            UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(
                this@LoginActivity,
                callback = callback
            )
        }
    }

    private fun getErrorLog(error: Throwable) {
        when {
            error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                Timber.e("접근이 거부 됨(동의 취소)")
            }
            error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                Timber.e("유효하지 않은 앱")
            }
            error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                Timber.e("인증 수단이 유효하지 않아 인증할 수 없는 상태")
            }
            error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                Timber.e("요청 파라미터 오류")
            }
            error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                Timber.e("유효하지 않은 scope ID")
            }
            error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                Timber.e("설정이 올바르지 않음(android key hash)")
            }
            error.toString() == AuthErrorCause.ServerError.toString() -> {
                Timber.e("서버 내부 에러")
            }
            error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                Timber.e("앱이 요청 권한이 없음")
            }
            else -> { // Unknown
                Timber.e("기타 에러")
            }
        }
    }
}