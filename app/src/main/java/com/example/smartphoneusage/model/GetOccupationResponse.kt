package com.example.smartphoneusage.model

data class GetOccupationResponse(
    val status: String,
    val occupation: String,
    val total: Int,
    val data: List<SmartphoneRecord>
)