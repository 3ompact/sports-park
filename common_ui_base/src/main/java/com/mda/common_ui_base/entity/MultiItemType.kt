package com.mda.common_ui_base.entity

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * recyclerview多类型处理
 */
class MultiItemType : MultiItemEntity {

    companion object {
        val TEXT: Int = 1
        val IMAGE: Int = 2
        val BANNER: Int = 3
        val GRID: Int = 4
        val IMAGEANDTEXT = 5
        val TEXTANDIMAGE = 6
        val SEARCHBAR = 7
        val QUCIKLY = 8
        val FIXED = 9
        val LEFTTITLEANDRIGHTMORE = 10

    }

    override val itemType: Int
        get() = this.itemType


}