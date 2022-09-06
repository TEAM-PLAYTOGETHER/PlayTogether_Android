package com.playtogether_android.data.singleton

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object PlayTogetherRepository {
    //카카오
    private const val KAKAO_USER_FIRST_NAME = "KAKAO_USER_FIRST_NAME" //유저네임
    private const val KAKAO_USER_LOG_OUT = "KAKAO_USER_LOG_OUT" //로그아웃 유무
    private const val KAKAO_UT_KEY = "KAKAO_UT_KEY"  //유저토큰 키
    private const val KAKAO_URT_KEY = "KAKAO_URT_KEY"  //유저 리프레시토큰 키
    private const val KAKAO_ACCESS = "KAKAO_ACCESS" //

    //구글
    private const val GOOGLE_USER_FIRST_NAME = "GOOGLE_USER_FIRST_NAME" //유저네임
    private const val GOOGLE_USER_LOG_OUT = "GOOGLE_USER_LOG_OUT" //로그아웃 유무
    private const val GOOGLE_UT_KEY = "GOOGLE_UT_KEY"  //유저토큰 키
    private const val GOOGLE_URT_KEY = "GOOGLE_URT_KEY"  //유저 리프레시토큰 키
    private const val GOOGLE_ACCESS = "GOOGLE_ACCESS" //

    //유저 동아리
    private const val CREW_ID = "CREW_ID"
    private const val CREW_NAME = "CREW_NAME"

    //getSharedPreferences
    private const val FB_KEY = "FB_TOKEN" //파이어베이슨 토큰 키
    private const val PREF_KEY = "PREF_KEY"  //authPreferences 키

    //현재 유저 상태
    private const val UT_KEY = "UT_KEY"  //유저토큰 키
    private const val USER_SOCIAL_KEY = "USER_SOCIAL_KEY" //소셜 키
    private const val USER_UUID_KEY = "USER_UUID_KEY" //유저아이디 키

    //푸시알림
    private const val PUSH_ALARM_KEY = "PUSH_ALARM_KEY" //푸시알림 온오프
    private const val PUSH_ALARM_EXIST_COUNT_KEY = "PUSH_ALARM_EXIST_COUNT_KEY" //푸시알림 개수

    private lateinit var preferences: SharedPreferences
    private lateinit var authPreferences: SharedPreferences
    private lateinit var masterKeyAlias: MasterKey


    fun init(context: Context) {
        masterKeyAlias = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        authPreferences = EncryptedSharedPreferences.create(
            context,
            PREF_KEY,
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    //동아리 이름
    var crewName: String
        get() = authPreferences.getString(CREW_NAME, "") ?: ""
        set(value) = authPreferences.edit { it.putString(CREW_NAME, value) }

    //동아리 id
    var crewId: Int
        get() = authPreferences.getInt(CREW_ID, -1) ?: -1
        set(value) = authPreferences.edit { it.putInt(CREW_ID, value) }

    //현재 유저 토큰
    var userToken: String
        get() = authPreferences.getString(UT_KEY, "") ?: ""
        set(value) = authPreferences.edit { it.putString(UT_KEY, value) }

    //현재 유저 소셜
    var userSocial: String
        get() = authPreferences.getString(USER_SOCIAL_KEY, "") ?: ""
        set(value) = authPreferences.edit { it.putString(USER_SOCIAL_KEY, value) }

    //현재 유저 아이디
    var userUuid: String
        get() = authPreferences.getString(USER_UUID_KEY, "") ?: ""
        set(value) = authPreferences.edit { it.putString(USER_UUID_KEY, value) }

    //구글 유저 토큰
    var googleUserToken: String
        get() = authPreferences.getString(GOOGLE_UT_KEY, "") ?: ""
        set(value) = authPreferences.edit { it.putString(GOOGLE_UT_KEY, value) }

    //구글 유저 리프레시 토큰
    var googleUserRefreshToken: String
        get() = authPreferences.getString(GOOGLE_URT_KEY, "") ?: ""
        set(value) = authPreferences.edit { it.putString(GOOGLE_URT_KEY, value) }

    //구글 소셜 로그인시 토큰
    var googleAccessToken: String
        get() = authPreferences.getString(GOOGLE_ACCESS, "") ?: ""
        set(value) = authPreferences.edit { it.putString(GOOGLE_ACCESS, value) }

    //구글 유저 이름
    var googleUserfirstName: String
        get() = authPreferences.getString(GOOGLE_USER_FIRST_NAME, "") ?: ""
        set(value) = authPreferences.edit { it.putString(GOOGLE_USER_FIRST_NAME, value) }

    //구글 유저 로그아웃 유무
    var googleUserlogOut: Boolean
        get() = preferences.getBoolean(GOOGLE_USER_LOG_OUT, false)
        set(value) = preferences.edit { it.putBoolean(GOOGLE_USER_LOG_OUT, value) }

    //카카오 유저 토큰
    var kakaoUserToken: String
        get() = authPreferences.getString(KAKAO_UT_KEY, "") ?: ""
        set(value) = authPreferences.edit { it.putString(KAKAO_UT_KEY, value) }

    //카카오 유저 리프레시 토큰
    var kakaoUserRefreshToken: String
        get() = authPreferences.getString(KAKAO_URT_KEY, "") ?: ""
        set(value) = authPreferences.edit { it.putString(KAKAO_URT_KEY, value) }

    //카카오 소셜 로그인시 토큰
    var kakaoAccessToken: String
        get() = authPreferences.getString(KAKAO_ACCESS, "") ?: ""
        set(value) = authPreferences.edit { it.putString(KAKAO_ACCESS, value) }

    //카카오 유저 이름
    var kakaoUserfirstName: String
        get() = authPreferences.getString(KAKAO_USER_FIRST_NAME, "") ?: ""
        set(value) = authPreferences.edit { it.putString(KAKAO_USER_FIRST_NAME, value) }

    //카카오 유저 로그아웃 유무
    var kakaoUserlogOut: Boolean
        get() = preferences.getBoolean(KAKAO_USER_LOG_OUT, false)
        set(value) = preferences.edit { it.putBoolean(KAKAO_USER_LOG_OUT, value) }

    //파이어베이스 토큰
    var fireBaseToken: String
        get() = authPreferences.getString(FB_KEY, "firebase") ?: ""
        set(value) = authPreferences.edit {
            it.putString(FB_KEY, value)
        }

    //푸시알림 온,오프
    var pushAlarmOn: Boolean
        get() = preferences.getBoolean(PUSH_ALARM_KEY, true)
        set(value) = preferences.edit {
            it.putBoolean(PUSH_ALARM_KEY, value)
        }

    //푸시알림 개수
    var alarmExistCount: Int
        get() = preferences.getInt(PUSH_ALARM_EXIST_COUNT_KEY, 0)
        set(value) = preferences.edit {
            it.putInt(PUSH_ALARM_EXIST_COUNT_KEY, value)
        }
}