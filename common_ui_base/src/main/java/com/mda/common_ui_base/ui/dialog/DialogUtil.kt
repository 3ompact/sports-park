package com.mda.common_ui_base.ui.dialog

import android.content.Context
import android.text.InputType
import com.mda.common_ui_base.R
import com.qmuiteam.qmui.skin.QMUISkinManager
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction

/**
 * 对话框工具类
 */
class DialogUtil {

    private val mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog

    /**
     * 一般形式对话框
     */
    fun showMessagePositiveDialog(
        context: Context,
        title: String,
        msg: String,
        listener: DialogActionListener
    ) {
        QMUIDialog.MessageDialogBuilder(context)
            .setTitle(title)
            .setMessage(msg)
            .setSkinManager(QMUISkinManager.defaultInstance(context))
            .addAction(
                R.string.cancel
            ) { dialog, index ->
                run {
                    dialog.dismiss()
                    listener.onNegative()
                }
            }.addAction(
                R.string.positive,
                QMUIDialogAction.ACTION_PROP_POSITIVE
            ) { dialog, index ->
                run {
                    dialog.dismiss()
                    listener.onPositive()
                }
            }.create(mCurrentDialogStyle).show()
    }

    /**
     * 可自己确定positive内容的dialog
     */
    fun showMessageNegativeDialog(
        context: Context,
        title: String,
        msg: String,
        actionName: Int = R.string.positive,
        listener: DialogActionListener
    ) {
        QMUIDialog.MessageDialogBuilder(context)
            .setTitle(title)
            .setMessage(msg)
            .setSkinManager(QMUISkinManager.defaultInstance(context))
            .addAction(R.string.cancel) { dialog, index ->
                run {
                    dialog.dismiss()
                    listener.onNegative()
                }
            }.addAction(
                actionName,
                QMUIDialogAction.ACTION_PROP_POSITIVE
            ) { dialog, index ->
                run {
                    dialog.dismiss()
                    listener.onPositive()
                }
            }.create(mCurrentDialogStyle).show()

    }


    /**
     * 长内容的dialog
     */
    fun showLongMessageDialog(
        context: Context,
        title: String,
        msg: String,
        actionName: Int = R.string.positive,
        listener: DialogActionListener
    ) {
        QMUIDialog.MessageDialogBuilder(context)
            .setTitle(title)
            .setMessage(msg)
            .setSkinManager(QMUISkinManager.defaultInstance(context))
            .addAction(actionName) { dialog, index ->
                run {
                    dialog.dismiss()
                    listener.onNegative()
                }
            }.create(mCurrentDialogStyle).show()

    }

    /**
     * 带checkbox的dialog
     */
    fun showConfirmAndCheckMessageDialog(
        context: Context,
        title: String,
        msg: String,
        check: Boolean,
        actionName: Int = R.string.cancel,
        actionName2: Int = R.string.positive,
        listener: DialogActionListener
    ) {
        QMUIDialog.CheckBoxMessageDialogBuilder(context)
            .setTitle(title)
            .setMessage(msg)
            .setChecked(check)
            .setSkinManager(QMUISkinManager.defaultInstance(context))
            .addAction(actionName) { dialog, index ->
                run {
                    dialog.dismiss()
                    listener.onNegative()
                }
            }.addAction(actionName2) { dialog, index ->
                run {
                    dialog.dismiss()
                    listener.onNegative()
                }
            }.create(mCurrentDialogStyle).show()

    }


    /**
     * 带菜单的dialog
     * 单选
     *
     */
    fun showMenuDialog(
        context: Context,
        title: String,
        actionName: Int = R.string.cancel,
        array: Array<String>,
        listener: DialogActionListener
    ) {
        QMUIDialog.MenuDialogBuilder(context)
            .setTitle(title)
            .setSkinManager(QMUISkinManager.defaultInstance(context))
            .addItems(array) { dialog, which ->
                run {
                    dialog.dismiss()
                    listener.onMenuItemClick(which)
                }
            }.addAction(actionName) { dialog, index ->
                run {
                    dialog.dismiss()
                    listener.onNegative()
                }
            }.create(mCurrentDialogStyle).show()

    }


    /**
     * 带菜单的dialog
     * 单选(带默认选项)
     *
     */
    fun showSingleChoiceMenuDialog(
        context: Context,
        title: String,
        actionName: Int = R.string.cancel,
        actionName2: Int = R.string.positive,
        array: Array<String>,
        itemSel: Int,
        listener: DialogActionListener
    ) {
        QMUIDialog.CheckableDialogBuilder(context)
            .setTitle(title)
            .setCheckedIndex(itemSel)
            .setSkinManager(QMUISkinManager.defaultInstance(context))
            .addItems(array) { dialog, which ->
                run {
                    listener.onMenuItemClick(which)
                }
            }.addAction(actionName) { dialog, index ->
                run {
                    dialog.dismiss()
                    listener.onNegative()
                }
            }
            .addAction(actionName2) { dialog, index ->
                run {
                    dialog.dismiss()
                    listener.onNegative()
                }
            }.create(mCurrentDialogStyle).show()

    }

    /**
     * 带菜单的dialog
     * 多选(不带默认选项)
     *  builder.getCheckedItemIndexes()
     */
    fun showMultiChoiceMenuDialog(
        context: Context,
        title: String,
        actionName: Int = R.string.cancel,
        array: Array<String>,
        actionName2: Int = R.string.positive,
        listener: DialogActionListener
    ) {
        val builder = QMUIDialog.MultiCheckableDialogBuilder(context)
            .setTitle(title)
            .setSkinManager(QMUISkinManager.defaultInstance(context))
            .addItems(array) { dialog, which ->
                run {
                    dialog.dismiss()
                    listener.onMenuItemClick(which)
                }
            }



        builder.addAction(actionName) { dialog, index ->
            run {
                dialog.dismiss()
                listener.onNegative()
            }
        }.addAction(actionName2) { dialog, index ->
            run {
                dialog.dismiss()
                listener.onNegative()
                listener.onMenuMultiItemClick(builder)
            }
        }.create(mCurrentDialogStyle).show()

    }

    /**
     * 带菜单的dialog
     * 多选(带默认选项)
     *  builder.getCheckedItemIndexes()
     */
    fun showMultiChoiceMenuDialog(
        context: Context,
        title: String,
        actionName: Int = R.string.cancel,
        actionName2: Int = R.string.positive,
        array: Array<String>,
        itemSel: IntArray,
        listener: DialogActionListener
    ) {
        val builder = QMUIDialog.MultiCheckableDialogBuilder(context)
            .setTitle(title)
            .setCheckedItems(itemSel)
            .setSkinManager(QMUISkinManager.defaultInstance(context))
            .addItems(array) { dialog, which ->
                run {
                    dialog.dismiss()
                    listener.onMenuItemClick(which)
                }
            }

        builder.addAction(actionName) { dialog, index ->
            run {
                dialog.dismiss()
                listener.onNegative()
            }
        }.addAction(actionName2) { dialog, index ->
            run {
                dialog.dismiss()
                listener.onNegative()
                listener.onMenuMultiItemClick(builder)

            }
        }.create(mCurrentDialogStyle).show()

    }


    /**
     * 带Numberous菜单的dialog
     * 多选
     *  builder.getCheckedItemIndexes()
     */
    fun showNumberousMultiChoiceMenuDialog(
        context: Context,
        title: String,
        actionName: Int = R.string.cancel,
        actionName2: Int = R.string.positive,
        array: Array<String>,
        itemSel: IntArray,
        listener: DialogActionListener
    ) {
        val builder = QMUIDialog.MultiCheckableDialogBuilder(context)
            .setTitle(title)
            .setCheckedItems(itemSel)
            .setSkinManager(QMUISkinManager.defaultInstance(context))
            .addItems(array) { dialog, which ->
                run {
                    dialog.dismiss()
                    listener.onMenuItemClick(which)
                }
            }

        builder.addAction(actionName) { dialog, index ->
            run {
                dialog.dismiss()
                listener.onNegative()
            }
        }.addAction(actionName2) { dialog, index ->
            run {
                dialog.dismiss()
                listener.onNegative()
                listener.onMenuMultiItemClick(builder)

            }
        }.create(mCurrentDialogStyle).show()

    }

    /**
     * 带编辑框的dialog
     *
     */
    fun showEditDialog(
        context: Context,
        title: String,
        placeholder: String,
        actionName: Int = R.string.cancel,
        actionName2: Int = R.string.positive,
        inputType: Int = InputType.TYPE_CLASS_TEXT,
        listener: DialogActionListener
    ) {
        val builder = QMUIDialog.EditTextDialogBuilder(context)
            .setTitle(title)
            .setPlaceholder(placeholder)
            .setSkinManager(QMUISkinManager.defaultInstance(context))
            .setInputType(inputType)




            builder.addAction(actionName) { dialog, index ->
                run {
                    dialog.dismiss()
                    listener.onNegative()
                }
            }.addAction(actionName2) { dialog, index ->
                run {
                    dialog.dismiss()
                    listener.onNegative()
                    listener.onEditContentListener(builder.editText.text.toString())
                }
            }.create(mCurrentDialogStyle).show()

    }

    interface DialogActionListener {
        //点击确定时的回调
        fun onPositive()

        //点击取消的回调
        fun onNegative()

        //菜单类型时的点击项回调
        fun onMenuItemClick(which: Int)

        //菜单类型可多选时的点击项回调
        fun onMenuMultiItemClick(builder: QMUIDialog.MultiCheckableDialogBuilder)

        //编辑框内容返回监听
        fun onEditContentListener(msg: String)
    }

}