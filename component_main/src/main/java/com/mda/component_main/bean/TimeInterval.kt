package com.mda.component_main.bean

data class SingleTimeInterval(
    val endTime: String,
    val id: Long,
    val isReserve: Int,
    val price: Int,
    val siteName :String = "一号场地",
    val startTime: String,
    val timeQuantumId: Long
)