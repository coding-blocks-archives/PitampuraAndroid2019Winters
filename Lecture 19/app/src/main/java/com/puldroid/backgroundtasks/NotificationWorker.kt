package com.puldroid.backgroundtasks

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(val context:Context,val workerParameters: WorkerParameters):Worker(context,workerParameters) {
    override fun doWork(): Result {
        createNotification(workerParameters.inputData.getString("TITLE")?:"Default title", "this is a description")
        return Result.success()
    }

    private fun createNotification(title: String, description: String) {
        val nMgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val channel = NotificationChannel("1",
                "Default Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.apply {
                enableVibration(true)
                enableLights(true)
            }
            nMgr.createNotificationChannel(channel)
        }

        val builder = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            Notification.Builder(context, "1")
        } else {
            //Create Heads up for Devices API < Oreo
            Notification.Builder(context)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE)

        }
        val notification = builder
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .build()
        nMgr.notify(System.currentTimeMillis().toInt(),notification)
    }

}