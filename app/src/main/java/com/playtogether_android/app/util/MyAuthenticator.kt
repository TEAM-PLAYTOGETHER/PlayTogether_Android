package com.playtogether_android.app.util

import com.playtogether_android.data.singleton.PlayTogetherRepository
import com.playtogether_android.domain.model.sign.IssuanceItem
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
        // write code to refresh the token
        val code = TokenManager().refresh()
        if (code == 200) {
            response.request.newBuilder()
                .header("Authorization", PlayTogetherRepository.userToken)
                .build()
        } else {
            return null
        }

        return null
    }

    //
    private val Response.responseCount: Int
        get() = generateSequence(this) { it.priorResponse }.count()

}