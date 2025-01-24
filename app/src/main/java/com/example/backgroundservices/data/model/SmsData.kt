package com.example.backgroundservices.data.model

data class SmsData(
    val address: String, // Sender's phone number
    val body: String,    // SMS content
    val date: Long       // Timestamp of the SMS
)