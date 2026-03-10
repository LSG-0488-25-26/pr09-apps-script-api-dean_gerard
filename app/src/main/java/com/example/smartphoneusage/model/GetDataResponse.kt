package com.example.smartphoneusage.model

data class GetDataResponse(
    val status: String,
    val totalRecords: Int,
    val data: List<SmartphoneRecord>
)