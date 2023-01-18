package com.playtogether_android.app.util

import android.provider.Settings.Global
import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.sign.IssuanceItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber

class MyAuthenticator : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // set maixmum retry count
        if (response.responseCount >= 3) {
            return null // If we've failed 3 times, give up.
        }
        Timber.d("init 401 떳을때 재발급")
        Timber.e("401 userToken : ${PlayTogetherRepository.userToken}")
        Timber.e("401 refreshToken : ${PlayTogetherRepository.userRefreshToken}")

        // write code to refresh the token
        var code = -1
        GlobalScope.launch {
            val job = TokenManager().getRefreshToken()
            job.join()
            if (job.isCompleted) code = 200
        }

        if (code == 200) {
            Timber.d("code 200 재발급")
            response.request.newBuilder()
                .header("Authorization", PlayTogetherRepository.userToken)
                .build()
        }
        return null
    }

    //
    private val Response.responseCount: Int
        get() = generateSequence(this) { it.priorResponse }.count()
}