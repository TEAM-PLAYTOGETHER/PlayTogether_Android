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
import com.playtogether_android.app.presentation.ui.main.MainActivity
import com.playtogether_android.app.presentation.ui.message.ChattingActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

// 대충 fcm 토큰 받으려고 긁어옴
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
            if (NotificationManagerCompat.from(this)
                .getNotificationChannel(Channel.CHATTING.name) == null
            ) {
                makeChannel()
            }
            sendNotification(remoteMessage)
        }
    }

    fun makeChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val mChannel = NotificationChannel(
                Channel.CHATTING.name,
                Channel.CHATTING.channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = Channel.CHATTING.description
            }
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManagerCompat.from(this).createNotificationChannel(mChannel)
            /*val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)*/
        }
    }

    private fun sendNotification(message: RemoteMessage) {
        val roomId = message.data.get("roomId").toString().toInt()
        val audienceId = message.data.get("sendId").toString().toInt()
        val name = message.data.get("title").toString()
        val body = message.data.get("body").toString()

        val stackBuilder = TaskStackBuilder.create(this)

        val parentIntent = Intent(this, MainActivity::class.java).run {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra("fragment", "message")
        }
        stackBuilder.addNextIntent(parentIntent)

        val intent = Intent(this, ChattingActivity::class.java).run {
            putExtra("name", name)
            putExtra("roomId", roomId)
            putExtra("audienceId", audienceId)
            setAction("" + Math.random())
        }
        stackBuilder.addNextIntent(intent)

        val rand = (Math.random().toFloat() * 10000).toInt()

        val pendingIntent: PendingIntent = stackBuilder.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                getPendingIntent(0, PendingIntent.FLAG_MUTABLE)
            } else {
                getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT)
            }
        }

        val builder = NotificationCompat.Builder(this, Channel.CHATTING.name).apply {
            setAutoCancel(true)
            setContentTitle(name)
            setContentText(body)
            setSmallIcon(R.mipmap.ic_launcher)
            setContentIntent(pendingIntent)
        }

        NotificationManagerCompat.from(this).notify(rand, builder.build())
    }
}

enum class Channel(val channelName: String, val description: String) {
    CHATTING("채팅", "채팅을 받는 푸시알림 권한")
}
