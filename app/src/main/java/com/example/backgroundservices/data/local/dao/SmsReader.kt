package com.example.backgroundservices.data.local.dao

import android.content.Context
import android.net.Uri
import com.example.backgroundservices.data.model.SmsData

class SmsReader(private val context: Context) {

    fun readSms(): List<SmsData> {
        val smsList = mutableListOf<SmsData>()
        val uri = Uri.parse("content://sms/inbox")
        val cursor = context.contentResolver.query(uri, null, null, null, null)

        cursor?.use {
            while (it.moveToNext()) {
                val address = it.getString(it.getColumnIndexOrThrow("address"))
                val body = it.getString(it.getColumnIndexOrThrow("body"))
                val date = it.getLong(it.getColumnIndexOrThrow("date"))
                if (isMomoTransaction(body)) {
                    smsList.add(SmsData(address, body, date))
                }
            }
        }
        return smsList
    }

    private fun isMomoTransaction(body: String): Boolean {
        // Use regex or keywords to identify Momo transactions
        return body.contains("momo", ignoreCase = true) ||
                body.contains("mobile money", ignoreCase = true)
    }
}