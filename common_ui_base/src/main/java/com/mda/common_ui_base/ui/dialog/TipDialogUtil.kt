package com.mda.common_ui_base.ui.dialog

import android.content.Context
import com.mda.common_ui_base.R
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog

/**
 * 弹出消息框工具类
 */
class TipDialogUtil {

    /**
     * 获取一般实例
     */
    fun getNormalInstance(context: Context, title: String): QMUITipDialog {
        return QMUITipDialog.Builder(context).setTipWord(title).create()
    }

    /**
     * 获取带icon实例
     */
    fun getWithIconInstance(context: Context, title: String, iconType: Int): QMUITipDialog {
        return QMUITipDialog.Builder(context).setIconType(iconType).setTipWord(title).create()
    }

    /**
     * 获取自定义布局实例
     */
    fun getWithCustomInstance(context: Context, layoutRes: Int): QMUITipDialog {
        return QMUITipDialog.CustomBuilder(context).setContent(layoutRes).create()
    }

    /**
     * 加载中
     * dialog
     */
    fun showLodingDialog(context: Context) {
        val tipDialog = QMUITipDialog.Builder(context)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
            .setTipWord("正在加载")
            .create()
            .show()
    }

    /**
     * 加载中
     * dialog
     * 获取示例
     */
    fun getLodingDialog(context: Context): QMUITipDialog {
        return QMUITipDialog.Builder(context)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
            .setTipWord("正在加载")
            .create()
    }
}