package com.puldroid.backgroundtasks

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startOneOffWork()

    }

    private fun startOneOffWork() {
        val request = OneTimeWorkRequestBuilder<NotificationWorker>()
                .build()
        WorkManager.getInstance(this).enqueue(request)
   }

}

