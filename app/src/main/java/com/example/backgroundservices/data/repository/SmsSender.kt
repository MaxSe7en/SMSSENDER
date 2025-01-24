package com.example.backgroundservices.data.repository

import com.example.backgroundservices.data.model.SmsData
import com.example.backgroundservices.data.network.ApiService


class SmsSender(private val apiService: ApiService) {
    suspend fun sendSms(smsData: SmsData) {
        try {
            val response = apiService.sendSms(smsData)
            if (!response.isSuccessful) {
                // Handle error
            }
        } catch (e: Exception) {
            // Handle exception
        }
    }
}