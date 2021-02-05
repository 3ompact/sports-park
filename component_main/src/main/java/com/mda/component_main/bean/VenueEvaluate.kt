package com.mda.component_main.bean

/**
 * 场馆详情页  评论数据类
 * @param avatarName 头像地址
 *
 */
data class VenueEvaluate(
    val avatarName: String,
    val createDate: String,
    val evaluationContent: String,
    val id: Long,
    val nickName: String,
    val pictureAdd: List<String>?,
    val score: Int
)