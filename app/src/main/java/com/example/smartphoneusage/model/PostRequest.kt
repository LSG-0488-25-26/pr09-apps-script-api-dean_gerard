package com.example.smartphoneusage.model

data class PostRequest(
    val apiKey: String,
    val type: String = "insert",
    val age: Int,
    val gender: String,
    val occupation: String,
    val dailyPhoneHours: Double,
    val sleepHours: Double
)