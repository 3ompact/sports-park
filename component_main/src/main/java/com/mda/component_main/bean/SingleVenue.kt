package com.mda.component_main.bean
//val list:MutableList<SingleTimeInterval>
/**
 * @param sequence 顺序号
 */
data class SingleVenue(
    val date: String,
    val projectId: Long,
    val projectName: String,
    val projectSiteId: Long,
    val siteName: String,
    val sequence:Int,
    val list:MutableList<SingleTimeInterval>
)