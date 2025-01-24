package com.example.backgroundservices.workers

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object SmsWorkManagerInitializer {
    fun scheduleSmsWorker(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<SmsWorker>(1, TimeUnit.HOURS).build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "SmsWorker",
            ExistingPeriodicWorkPolicy.KEEP, // Avoid duplicate work requests
            workRequest
        )
    }
}
