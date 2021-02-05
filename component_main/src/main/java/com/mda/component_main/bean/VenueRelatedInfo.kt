package com.mda.component_main.bean

/**
 * 场馆相关信息
 */
data class VenueRelatedInfo(
    val addressNavigation: String,
    val bathFacilities: String,
    val businessHours: String,
    val createDate: String,
    val equipmentRental: String,
    val facilities: String,
    val id: Long,
    val pictureAdds: List<String>?,
    val remark: String,
    val stadiumAdd: String,
    val stadiumName: String,
    val stadiumPhone: String,
    val status: Int,
    val updateDate: String
)