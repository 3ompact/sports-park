package com.mda.component_main.ui.activity

import android.R.attr.fontStyle
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bin.david.form.core.SmartTable
import com.bin.david.form.core.TableConfig
import com.bin.david.form.data.CellInfo
import com.bin.david.form.data.column.Column
import com.bin.david.form.data.format.draw.IDrawFormat
import com.bin.david.form.data.style.LineStyle
import com.bin.david.form.data.table.ArrayTableData
import com.bin.david.form.data.table.TableData
import com.bin.david.form.utils.DensityUtils
import com.mda.common_ui_base.base.BaseVMDBActivity
import com.mda.common_ui_base.base.BaseViewModel
import com.mda.component_main.R
import com.mda.component_main.databinding.ActivityVenueSelectionBinding


class VenueSelectionActivity : BaseVMDBActivity<BaseViewModel, ActivityVenueSelectionBinding>() {
    private lateinit var table: SmartTable<Int>
    override fun layoutId(): Int {
       return  R.layout.activity_venue_selection
    }

    override fun actionBar(): Boolean {
        return false
    }

    override fun initView() {
        table = mDataBinding.tableSsss as SmartTable<Int>

        val week = arrayOf("日", "一", "二", "三", "四", "五", "六")
        val infos = arrayOf(
            arrayOf(0, 1, 2, 1, 1, 0, 1, 1, 0, 1, 1, 2, 3),
            arrayOf(4, 2, 1, 1, 0, 1, 1, 0, 1, 1, 2, 2, 3),
            arrayOf(2, 2, 0, 1, 2, 4, 1, 0, 1, 3, 0, 1, 1),
            arrayOf(2, 1, 1, 0, 1, 4, 0, 1, 1, 2, 2, 0, 3),
            arrayOf(0, 1, 2, 4, 1, 0, 1, 4, 0, 1, 1, 2, 2),
            arrayOf(1, 0, 1, 3, 2, 2, 0, 1, 2, 1, 1, 0, 4),
            arrayOf(3, 1, 2, 4, 0, 1, 2, 1, 1, 0, 1, 1, 0)
        )

//        table.config.setColumnTitleStyle(fontStyle)
        table.config.horizontalPadding = 0
        table.config.setFixedXSequence(true)
        table.config.setFixedYSequence(true)
        table.config.setSequenceGridStyle(LineStyle(0.1f,R.color.white))
        table.config.setColumnTitleGridStyle(LineStyle(0.1f,R.color.white))
        table.config.setShowXSequence(false)
        table.config.verticalPadding = 0
        table.config.contentGridStyle = LineStyle()

        val tableData: ArrayTableData<Int> =
            ArrayTableData.create("日程表", week, infos, object : IDrawFormat<Int> {
                override fun measureWidth(
                    column: Column<Int?>,
                    position: Int,
                    config: TableConfig
                ): Int {
                    return DensityUtils.dp2px(this@VenueSelectionActivity, 50f)
                }

                override fun measureHeight(
                    column: Column<Int?>,
                    position: Int,
                    config: TableConfig
                ): Int {
                    return DensityUtils.dp2px(this@VenueSelectionActivity, 50f)
                }

                override fun draw(
                    c: Canvas,
                    rect: Rect,
                    cellInfo: CellInfo<Int?>,
                    config: TableConfig
                ) {
                    val paint: Paint = config.paint
                    val color: Int
                    when (cellInfo.data) {
                        1 -> color = R.color.purple_200
                        2 -> color = R.color.purple_500
                        3 -> color = R.color.purple_700
                        4 -> color = R.color.teal_200
                        else -> color = R.color.teal_700
                    }
                    paint.setStyle(Paint.Style.FILL)
                    paint.setColor(ContextCompat.getColor(this@VenueSelectionActivity, color))
                    c.drawRect(rect.left + 5f, rect.top + 5f, rect.right - 5f, rect.bottom - 5f, paint)
                }
            })
        tableData.onItemClickListener = object : TableData.OnItemClickListener<Int> {
            override fun onClick(column: Column<Int>, value: String, t: Int, col: Int, row: Int) {
                tableData.arrayColumns[col].datas[row]
                Toast.makeText(
                    this@VenueSelectionActivity,
                    "列:" + col + " 行：" + row + "数据：" + value,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        table.setTableData(tableData)
    }

    override fun initData() {
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

    override fun createObserver() {
    }
}