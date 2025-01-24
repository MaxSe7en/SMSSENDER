package com.example.backgroundservices.services


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.d("BootReceiver", "Device booted, starting service...")

            // Start your service or background task here
            val serviceIntent = Intent(context, SmsService::class.java)
            context.startService(serviceIntent) // For older Android versions
            // For Android 8.0 (API 26) and above, use startForegroundService
            // context.startForegroundService(serviceIntent)
        }
    }
}