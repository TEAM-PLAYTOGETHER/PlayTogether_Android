package com.playtogether_android.app.util.permission

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.playtogether_android.app.util.Channel
import com.playtogether_android.app.util.shortToast
import com.playtogether_android.data.singleton.PlayTogetherRepository

object NotificationPermission {
    fun requestNotificationPermission(context: Context) {
        if (PlayTogetherRepository.doesNotificationChecked) return
        if (!NotificationManagerCompat.from(context).areNotificationsEnabled()) {
            getNotificationPermissionByTed(context)
            return
        }
        // 채널 생성
        val channel = NotificationChannel(
            Channel.CHATTING.name,
            Channel.CHATTING.channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        NotificationManagerCompat.from(context).createNotificationChannel(channel)
    }

    private fun getNotificationPermissionByTed(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val tedBuilder = TedPermission.create()
                .setPermissionListener(object : PermissionListener {
                    override fun onPermissionGranted() {
                        context.shortToast("권한요청이 승인되었습니다.")
                        PlayTogetherRepository.doesNotificationChecked = true
                    }

                    override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                        context.shortToast("권한요청이 거절되었습니다..")
                        PlayTogetherRepository.doesNotificationChecked = true
                    }
                })
            tedBuilder.setPermissions(Manifest.permission.POST_NOTIFICATIONS).check()
        }
    }
}
