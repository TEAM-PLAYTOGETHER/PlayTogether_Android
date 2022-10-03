package com.playtogether_android.app.util

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.playtogether_android.app.presentation.ui.message.ChattingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val TAG: String = "MsgService"

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d(TAG, "From : ${message.from}")
        if (message.data.size > 0) {
            Log.d(TAG, "data : ${message.data.toString()}")
        } else {
            Log.d(TAG, "data is empty")
        }

        if (message.notification != null){
            Log.d(TAG,"notification title : ${message.notification!!.title}")
            Log.d(TAG,"notification body : ${message.notification!!.body}")
        } else{
            Log.d(TAG,"notification is empty")
        }
    }

    private fun sendNotification(title : String, body : String){
        val intent = Intent(this, ChattingActivity::class.java)

    }
}