package com.puldroid.backgroundtasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startOneOffWork()
        startPeriodWork()

    }

    private fun startPeriodWork() {
        val workerParameters = workDataOf(
                "TITLE" to "Periodic Work"
        )
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)

                .build()
        val request = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setInputData(workerParameters)
                .build()

        WorkManager.getInstance(this).enqueue(request)

    }

    private fun startOneOffWork() {
        val workerParameters = workDataOf(
                "TITLE" to "One Time Work"
        )
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)

                .build()
        val request = OneTimeWorkRequestBuilder<NotificationWorker>()
                .setConstraints(constraints)
                .setInputData(workerParameters)
//                .setInitialDelay(20000,TimeUnit.MILLISECONDS)
                .build()
        WorkManager.getInstance(this).enqueue(request)
    }

}

