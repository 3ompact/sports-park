package com.mda.component_main.bean

/**
 * 单个类别项目data类
 * @param statue 0 该场馆已启用 1    2    3
 *
 */
data class VenueProject(
    val createDate: String,
    val id: String,
    val projectName: String,
    val remark: String,
    val stadiumId: Long,
    val status: Int,
    val updateDate: String
)