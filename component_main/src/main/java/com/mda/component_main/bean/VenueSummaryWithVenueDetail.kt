package com.mda.component_main.bean

/**
 * homefragment 场馆摘要数据类(除去项目分类和单项目日期)
 */
data class VenueSummaryWithVenueDetail(
    val addressNavigation: String,
    val bathFacilities: String,
    val businessHours: String,
    val createDate: String,
    val equipmentRental: String,
    val facilities: String,
    val id: Long,
    val pictureAdds: MutableList<String>,
    val remark: String,
    val stadiumAdd: String,
    val stadiumName: String,
    val stadiumPhone: String,
    val status: Int,
    val updateDate: String,
    val labelName: MutableList<String>,
    val averageScore: String,
    val evaluationNumber: String
)