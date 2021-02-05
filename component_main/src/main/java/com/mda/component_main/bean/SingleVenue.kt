package com.mda.component_main.bean
//val list:MutableList<SingleTimeInterval>
data class SingleVenue(
    val date: String,
    val projectId: Long,
    val projectName: String,
    val projectSiteId: Long,
    val siteName: String,
    val list:MutableList<SingleTimeInterval>
)