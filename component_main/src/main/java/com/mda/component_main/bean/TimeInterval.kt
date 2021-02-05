package com.mda.component_main.bean

data class SingleTimeInterval(
    val endTime: String,
    val id: Long,
    val isReserve: Int,
    val price: Int,
    val startTime: String,
    val timeQuantumId: Long
)