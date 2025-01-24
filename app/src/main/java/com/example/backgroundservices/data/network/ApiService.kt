package com.example.backgroundservices.data.network

import com.example.backgroundservices.data.model.SmsData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("sms")
    suspend fun sendSms(@Body smsData: SmsData): Response<Unit>
}