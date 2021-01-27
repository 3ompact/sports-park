package com.mda.component_main.ui.activity

import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.bin.david.form.core.SmartTable
import com.bin.david.form.core.TableConfig
import com.bin.david.form.data.CellInfo
import com.bin.david.form.data.column.Column
import com.bin.david.form.data.format.draw.IDrawFormat
import com.bin.david.form.data.format.grid.BaseGridFormat
import com.bin.david.form.data.format.sequence.ISequenceFormat
import com.bin.david.form.data.style.LineStyle
import com.bin.david.form.data.table.ArrayTableData
import com.bin.david.form.data.table.TableData
import com.bin.david.form.utils.DensityUtils
import com.bin.david.form.utils.DrawUtils
import com.mda.basics_lib.utils.PhoneInfo
import com.mda.common_ui_base.base.BaseVMDBActivity
import com.mda.common_ui_base.base.BaseViewModel
import com.mda.component_main.R
import com.mda.component_main.databinding.ActivityVenueSelectionBinding
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.qmuiteam.qmui.widget.tab.*

@Route(path = "/cm/venueselectionactivity")
class VenueSelectionActivity : BaseVMDBActivity<BaseViewModel, ActivityVenueSelectionBinding>() {
    private lateinit var table: SmartTable<Int>
    private lateinit var mTopBar : QMUITopBarLayout
    private lateinit var mTabSag : QMUITabSegment2

    override fun layoutId(): Int {
        return R.layout.activity_venue_selection
    }

    override fun actionBar(): Boolean {
        return false
    }

    override fun initView() {
        table = mDataBinding.tableSsss as SmartTable<Int>

        mTopBar = mDataBinding.topbarVenueSelectionActivity

        mTopBar.setTitle("场次选择")
        mTopBar.addLeftBackImageButton().setImageDrawable(resources.getDrawable(R.drawable.icon_left_back))
//            .setBackgroundColor(Color.parseColor("#333333"))
//            .setImageDrawable(resources.getDrawable(R.drawable.icon_left_back))

        mTabSag = mDataBinding.tabsagWeekVenueSelectionActivity
        var tabBuilder : QMUITabBuilder =  mTabSag.tabBuilder()
//        .build(this@VenueSelectionActivity)
//        CustomeQMUITab("ssss\nssssss")
        initTabSagment()
        inittable(table)

    }

    //初始化tagsagment
    fun initTabSagment(){
        var tabBuilder : QMUITabBuilder =  mTabSag.tabBuilder().setIconPosition(QMUITab.ICON_POSITION_TOP)
        mTabSag.setMode(QMUITabSegment.MODE_SCROLLABLE)
        mTabSag.setPadding(40, 0, 40, 0)
        mTabSag.setIndicator(
            QMUITabIndicator(
                4, false, true
            )
        )
        val component = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.mipmap.icon_tabbar_component_selected1
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.mipmap.testssss
                )
            )
            .setText("1月11日")
            .build(this@VenueSelectionActivity)

        val component1 = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.mipmap.icon_tabbar_component_selected
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.week_test2
                )
            )
            .setText("1月12日")
            .build(this@VenueSelectionActivity)

        val component2 = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.mipmap.icon_tabbar_component_selected
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.week_test2
                )
            )
            .setText("1月13日")
            .build(this@VenueSelectionActivity)

        val component3 = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.mipmap.icon_tabbar_component_selected
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.week_test2
                )
            )
            .setText("1月14日")
            .build(this@VenueSelectionActivity)
        val component4 = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.mipmap.icon_tabbar_component_selected
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.week_test2
                )
            )
            .setText("1月15日")
            .build(this@VenueSelectionActivity)

        val component5 = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.mipmap.icon_tabbar_component_selected
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.week_test2
                )
            )
            .setText("1月17日")
            .build(this@VenueSelectionActivity)

        val component6 = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.mipmap.icon_tabbar_component_selected
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.week_test2
                )
            )
            .setText("1月17日")
            .build(this@VenueSelectionActivity)
        mTabSag.addTab(
            component
        )
        mTabSag.addTab(
            component1
        )
        mTabSag.addTab(
            component2
        )
        mTabSag.addTab(
            component3
        )
        mTabSag.addTab(
            component4
        )
        mTabSag.addTab(
            component5
        )
        mTabSag.addTab(
            component6
        )
        mTabSag.notifyDataChanged()
    }

    //初始化场地选择器
    fun inittable(table: SmartTable<Int>) {
        val week = arrayOf("日", "一", "二", "三", "四", "五", "六")
        //按照列数据展示数据
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
        table.config.setSequenceGridStyle(LineStyle(0.1f, R.color.white))
        table.config.setColumnTitleGridStyle(LineStyle(0.1f, R.color.white))
        table.config.setShowXSequence(false)
        table.config.verticalPadding = 0
        table.config.contentGridStyle = LineStyle()


        table.config.tableGridFormat = object : BaseGridFormat() {
            override fun isShowYSequenceHorizontalLine(row: Int): Boolean {
                return false
            }

            override fun isShowYSequenceVerticalLine(row: Int): Boolean {
                return false
            }

            override fun isShowHorizontalLine(col: Int, row: Int, cellInfo: CellInfo<*>?): Boolean {
                return false
            }

            override fun isShowVerticalLine(col: Int, row: Int, cellInfo: CellInfo<*>?): Boolean {
                return col == 10
            }

            override fun drawTableBorderGrid(
                canvas: Canvas,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                paint: Paint
            ) {
            }
        }

        var s = 1111

        val tableData: ArrayTableData<Int> =
            ArrayTableData.create("", week, infos, object : IDrawFormat<Int> {
                var x: Int = 0
                var y: Int = 0

                override fun measureWidth(
                    column: Column<Int?>,
                    position: Int,
                    config: TableConfig
                ): Int {
                    x = position
                    return DensityUtils.dp2px(this@VenueSelectionActivity, 80f)
                }

                override fun measureHeight(
                    column: Column<Int?>,
                    position: Int,
                    config: TableConfig
                ): Int {
                    y = position
                    return DensityUtils.dp2px(this@VenueSelectionActivity, 40f)
                }

                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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
                    paint.setColor(
                        ContextCompat.getColor(
                            this@VenueSelectionActivity,
                            R.color.text_gray
                        )
                    )

                    //进行状态判断
                    when (true) {
                        true -> {

                        }

                    }


                    //为不可预订时的处理
                    var bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_test1)
                    var zoomX = PhoneInfo.getPhonDensity(this@VenueSelectionActivity.application)

                    var paintBitmap = Paint()
                    var paintBackground = Paint()
                    paintBackground.setColor(Color.parseColor("#3399fe"))
                    paintBackground.style = Paint.Style.STROKE
                    paintBackground.strokeWidth = 2f
                    paintBackground.isAntiAlias = true

                    //text
                    var paintText = Paint()
                    paintText.textSize = 13f * zoomX
                    var w = paintText.measureText("¥50")

                    c.drawText(
                        "¥50",
                        rect.centerX().toFloat() - w / 2,
                        rect.centerY().toFloat() - (paintText.ascent() + paintText.descent()) / 2,
                        paintText
                    )

                    var shader: Shader =
                        BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

                    c.drawRoundRect(
                        (rect.left + 5).toFloat(),
                        (rect.top + 5).toFloat(),
                        (rect.right - 5).toFloat(),
                        (rect.bottom - 5).toFloat(),
                        8f, 8f,
                        paintBackground
                    )

                    var widthM = (80f * zoomX - bitmap.width) / 2
                    var heightM = (40f * zoomX - bitmap.height) / 2


                    c.drawBitmap(
                        bitmap,
                        (rect.left + widthM),
                        (rect.top + heightM),
                        paintBitmap
                    )

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

        tableData.ySequenceFormat = object : ISequenceFormat {
            override fun format(t: Int?): String {
                when (t) {

                    0 -> return "场地"

                    else -> {


                        //TODO 返回时间列表
                        var s =
                            StringBuilder().append("09:00").append("\n").append("-").append("\n")
                                .append("11:00")
                        return s.toString()
                    }

                }
            }


            override fun draw(canvas: Canvas?, sequence: Int, rect: Rect?, config: TableConfig?) {
                var text1 = "Lorem\nindustry."
                var t = format((sequence))


//                val staticLayout2 = StaticLayout.Builder.obtain()

                //字体缩放
                val paint = config!!.paint
                val zoom: Float = if (config.getZoom() > 1) 1f else config.getZoom()
                paint.textSize = paint.textSize * zoom
                paint.textAlign = Paint.Align.CENTER


                var paint1 = TextPaint()
                paint1.isAntiAlias = true
                paint1.textAlign = Paint.Align.CENTER
                paint1.textSize = 25 * zoom
                paint1.setColor(Color.parseColor("#333333"))
//                paint1.setColor(Color.parseColor("#3399fe"))
                val staticLayout1 = StaticLayout(
                    t, paint1, 100,
                    Layout.Alignment.ALIGN_NORMAL, 1f, 0f, true
                )
//                staticLayout1.toString()
                if (sequence != 0 && sequence < 14) {

                    canvas!!.save()
                    canvas.translate(
                        rect!!.centerX().toFloat(),
                        (rect.centerY() - staticLayout1.height / 2).toFloat()
                    )
                    staticLayout1.draw(canvas!!)
                    canvas!!.restore()
                } else {
                    canvas!!.drawText(
                        format((sequence)), rect!!.centerX().toFloat(), DrawUtils.getTextCenterY(
                            rect.centerY(), paint
                        ), paint
                    )
                }


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