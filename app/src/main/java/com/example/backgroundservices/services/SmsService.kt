package com.example.backgroundservices.services

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.backgroundservices.R


import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class SmsService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("SmsService", "Service created")
    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("SmsService", "Service started")

        // Start your background task here (e.g., reading SMS)
        readSmsMessages()

        // For Android 8.0 (API 26) and above, you must start the service as a foreground service
        startForeground(1, createNotification())

        return START_STICKY // Ensures the service restarts if terminated by the system
    }

    private fun readSmsMessages() {
        // Implement your SMS reading logic here
        Log.d("SmsService", "Reading SMS messages...")
    }

    private fun createNotification(): Notification {
        // Create a notification for the foreground service
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "sms_service_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "SMS Service",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("SMS Service")
            .setContentText("Reading SMS messages in the background")
            .setSmallIcon(R.drawable.ic_notification)
            .build()
    }
}