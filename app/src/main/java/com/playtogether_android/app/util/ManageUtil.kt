package com.playtogether_android.app.util

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import timber.log.Timber

object ManageUtil {
    fun Context.restartApp() {
        val intent = this.packageManager.getLaunchIntentForPackage(packageName)
        val mainIntent = Intent.makeRestartActivityTask(intent?.component)
        startActivity(mainIntent)
        Runtime.getRuntime().exit(0)

        Timber.d("ManageUtil: Application Restart")
    }

    fun Context.isServiceRunning(className: String): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val activityName = manager.appTasks[0].taskInfo.topActivity?.className

        return className == activityName
    }

}