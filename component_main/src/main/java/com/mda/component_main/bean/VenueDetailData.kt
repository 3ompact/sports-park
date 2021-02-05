package com.mda.component_main.bean

/**
 * 场馆详情封装类
 */
data class VenueDetailData(
    val sysStadium: VenueSummaryWithVenueDetail?,
    val dateVO: MutableList<VenueDate>?,
    val stadiumEvaluationVO: MutableList<VenueEvaluate>?
){
    constructor():this(null,null,null)
}
