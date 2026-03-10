package com.example.smartphoneusage.model

data class GetStatsResponse(
    val status: String,
    val avgPhoneHours: Double,
    val avgSleepHours: Double
)