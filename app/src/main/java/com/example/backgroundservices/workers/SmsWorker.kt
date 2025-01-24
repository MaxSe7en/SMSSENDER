package com.example.backgroundservices.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.backgroundservices.data.local.dao.SmsReader
import com.example.backgroundservices.data.network.RetrofitClient
import com.example.backgroundservices.data.repository.SmsSender

class SmsWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val smsReader = SmsReader(applicationContext)
        val smsList = smsReader.readSms()
        val smsSender = SmsSender(RetrofitClient.apiService)

        smsList.forEach { smsData ->
            smsSender.sendSms(smsData)
        }

        return Result.success()
    }
}