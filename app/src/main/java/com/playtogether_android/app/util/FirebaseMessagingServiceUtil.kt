package com.playtogether_android.app.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.playtogether_android.app.R
import com.playtogether_android.app.presentation.ui.message.ChattingActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

// 대충 fcm 토큰 받으려고 긁어옴
const val CHANNEL_ID = "plto noti channel"
const val NOTIFICATION_ID = 82

@AndroidEntryPoint
class FirebaseMessagingServiceUtil : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data.isEmpty()) {
            Timber.e("plto fcm : remote message data is empty")
        } else {
            Timber.e("plto fcm : remote message data is not empty")
            makeChannel()
            sendNotification(remoteMessage)
        }

        /*if (remoteMessage.notification == null) {
            Timber.e("plto fcm : remote message notification is empty")
        } else {
            Timber.e("plto fcm : remote message notification is not empty")
            makeChannel()
            sendNotification(remoteMessage)
        }*/
    }

    fun makeChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    private fun sendNotification(message: RemoteMessage) {
        val roomId = message.data.get("roomId").toString().toInt()
        val audienceId = message.data.get("sendId").toString().toInt()
        //val name = message.notification!!.title.toString()
        val name = message.data.get("title").toString()
        val body = message.data.get("body").toString()

        val intent = Intent(this, ChattingActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("roomId", roomId)
        intent.putExtra("audienceId", audienceId)

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        }else {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setAutoCancel(true)
            setContentTitle(name)
            setContentText(body)
            setSmallIcon(R.mipmap.ic_launcher)
            setContentIntent(pendingIntent)
        }

        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, builder.build())
    }
}