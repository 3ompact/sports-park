package com.mda.component_main.ui.activity

import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.bin.david.form.core.SmartTable
import com.bin.david.form.core.TableConfig
import com.bin.david.form.data.CellInfo
import com.bin.david.form.data.column.Column
import com.bin.david.form.data.format.draw.IDrawFormat
import com.bin.david.form.data.format.grid.BaseGridFormat
import com.bin.david.form.data.format.sequence.ISequenceFormat
import com.bin.david.form.data.style.FontStyle
import com.bin.david.form.data.style.LineStyle
import com.bin.david.form.data.table.ArrayTableData
import com.bin.david.form.data.table.TableData
import com.bin.david.form.utils.DensityUtils
import com.bin.david.form.utils.DrawUtils
import com.mda.basics_lib.`interface`.OnResponseListener
import com.mda.basics_lib.log.LogUtil
import com.mda.basics_lib.utils.PhoneInfo
import com.mda.basics_lib.utils.SpannerableStringUtil
import com.mda.common_ui_base.base.BaseVMDBActivity
import com.mda.component_main.R
import com.mda.component_main.adapter.VenueSelectionAdapter
import com.mda.component_main.bean.SingleTimeInterval
import com.mda.component_main.bean.SingleVenue
import com.mda.component_main.bean.VenueSelectionData
import com.mda.component_main.databinding.ActivityVenueSelectionBinding
import com.mda.component_main.decoration.VenueSelectionActivityRecyclerHorizontalViewDecoration
import com.mda.component_main.viewmodel.VenueSelectionActivityModel
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton
import com.qmuiteam.qmui.widget.QMUIEmptyView
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.qmuiteam.qmui.widget.tab.*
import java.lang.reflect.Method
import java.util.*
import java.util.stream.Collectors
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Route(path = "/cm/venueselectionactivity")
class VenueSelectionActivity :
    BaseVMDBActivity<VenueSelectionActivityModel, ActivityVenueSelectionBinding>() {
    private lateinit var table: SmartTable<Int>
    private lateinit var mTopBar: QMUITopBarLayout
    private lateinit var mTabSag: QMUITabSegment2

    private lateinit var rv: RecyclerView
    lateinit var adapter: VenueSelectionAdapter
    private lateinit var tvSelNum: TextView
    private lateinit var tvTotalMoney: TextView
    private lateinit var emptyView:QMUIEmptyView

    @JvmField
    @Autowired
    var id: Long = 0

    @JvmField
    @Autowired
    var time: String = ""

    //    val infoss = arrayOf(
//        arrayOf(0),
//        arrayOf(0),
//        arrayOf(0)
//    )

    private lateinit var originData: List<SingleVenue>

    var infoss: Array<Array<Int?>?> = arrayOf(
        arrayOf(0),
        arrayOf(0),
        arrayOf(0)
    )
    var test: Array<Array<Int?>?> = arrayOf()


    //    lateinit var newInfoC:Array<Array<Int?>?>
//    lateinit var newInfoR:Array<Int?>
//
//    lateinit var newMoneyC:Array<Array<Int?>>
//    lateinit var newMoneyR:Array<Int?>
//
//    lateinit var newColName:Array<String?>
    //该运动项目时间表数据
    val venueInfoList: MutableList<SingleVenue> = ArrayList()

    //已选则数据
    var arrayTwoDiemn: MutableList<SingleTimeInterval> = ArrayList()
    var mapDuplicate: TreeMap<Long, SingleTimeInterval> = TreeMap()

    override fun layoutId(): Int {
        return R.layout.activity_venue_selection
    }

    override fun actionBar(): Boolean {
        return false
    }

    override fun initView() {
        tvSelNum = mDataBinding.tvSelectedVenueSelectionActivity
        tvTotalMoney = mDataBinding.tvMoneyVenueSelectionActivity

        table = mDataBinding.tableSsss as SmartTable<Int>

        mTopBar = mDataBinding.topbarVenueSelectionActivity

        mTopBar.setTitle("场次选择")

        emptyView = mDataBinding.emptyviewVenueSelectionActivity
        emptyView.hide()
        var ivB: QMUIAlphaImageButton = mTopBar.addLeftBackImageButton()
        ivB.setOnClickListener {
            finish()
        }
        ivB.setImageDrawable(resources.getDrawable(R.drawable.icon_left_back))

        rv = mDataBinding.rvSelectedVenueSelectionActivity
        mTabSag = mDataBinding.tabsagWeekVenueSelectionActivity
        initRV()
        initTabSagment()
//        inittable(table)

    }

    //初始化recyclerview
    fun initRV() {
        val lm = LinearLayoutManager(this@VenueSelectionActivity)
        lm.orientation = RecyclerView.HORIZONTAL
        adapter = VenueSelectionAdapter(this@VenueSelectionActivity, arrayTwoDiemn)
        rv.layoutManager = lm
        rv.addItemDecoration(VenueSelectionActivityRecyclerHorizontalViewDecoration())
        rv.adapter = adapter


    }

    //初始化tagsagment
    fun initTabSagment() {
        var tabBuilder: QMUITabBuilder =
            mTabSag.tabBuilder().setGravity(Gravity.CENTER)
        mTabSag.setMode(QMUITabSegment.MODE_SCROLLABLE)
//        mTabSag.setPadding(40, 0, 40, 0)


//        mTabSag.reset()
//        mTabSag.setIndicator(null)
//        tabBuilder.setDynamicChangeIconColor(true)
//        val component = tabBuilder
//            .setNormalDrawable(ContextCompat.getDrawable(this, R.drawable.icon_test_monday))
//            .setSelectedDrawable(
//                ContextCompat.getDrawable(
//                    this,
//                    R.drawable.icon_test_monday1
//                )
//            ).setIconPosition(QMUITab.ICON_POSITION_TOP)
//            .setText("Components")
//            .build(this)
//        val util = tabBuilder
//            .setNormalDrawable(ContextCompat.getDrawable(this, R.drawable.icon_test_monday))
//            .setSelectedDrawable(
//                ContextCompat.getDrawable(
//                    this,
//                    R.drawable.icon_monday_selected
//                )
//            ).setIconPosition(QMUITab.ICON_POSITION_TOP)
//            .setText("Helper")
//            .build(this)
//        mTabSag.addTab(component)
//        mTabSag.addTab(util)

//        mTabSag.setIndicator(
//            QMUITabIndicator(
//                4, false, true
//            )
//        )
        val component = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.icon_monday_normal
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.icon_monday_normal
                )
            ).setIconPosition(QMUITab.ICON_POSITION_TOP)
            .setTextSize(40, 40)
            .setText("1月11日")
            .build(this)

        val component1 = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.icon_tuesday_normal
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.icon_tuesday_normal
                )
            ).setIconPosition(QMUITab.ICON_POSITION_TOP)
            .setText("1月12日")
            .build(this@VenueSelectionActivity)

        val component2 = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.icon_wednesday_normal
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.icon_wednesday_normal
                )
            ).setIconPosition(QMUITab.ICON_POSITION_TOP)
            .setText("1月13日")
            .build(this@VenueSelectionActivity)

        val component3 = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.icon_thursday_normal
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.icon_thursday_normal
                )
            ).setIconPosition(QMUITab.ICON_POSITION_TOP)
            .setText("1月14日")
            .build(this@VenueSelectionActivity)
        val component4 = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.icon_friday_normal
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.icon_friday_normal
                )
            ).setIconPosition(QMUITab.ICON_POSITION_TOP)
            .setText("1月15日")
            .build(this@VenueSelectionActivity)

        val component5 = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.icon_saturday_normal
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.icon_saturday_normal
                )
            ).setIconPosition(QMUITab.ICON_POSITION_TOP)
            .setText("1月17日")
            .build(this@VenueSelectionActivity)

        val component6 = tabBuilder
            .setNormalDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.icon_sunday_normal
                )
            )
            .setSelectedDrawable(
                ContextCompat.getDrawable(
                    this@VenueSelectionActivity,
                    R.drawable.icon_sunday_normal
                )
            ).setIconPosition(QMUITab.ICON_POSITION_TOP)
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
        mTabSag.selectTab(0)
//        mTabSag.selectTab(0)
        mTabSag.notifyDataChanged()
//        mTabSag.selectTab(0)
//        inittable(table)
        //, colName: Array<String?>, infos: Array<Array<Int?>?>
    }

    //初始化场地选择器
    fun inittable(table: SmartTable<Int>, colName: Array<String?>, infos: Array<Array<Int?>?>,money: Array<Array<Int?>?>) {
        val week = arrayOf("日", "一", "二", "三", "四", "五", "六")
        //按照列数据展示数据


        val fontStyle = FontStyle(this, 10, ContextCompat.getColor(this, R.color.text_black))
        table.config.setColumnTitleStyle(fontStyle)
        table.config.horizontalPadding = 0
        table.config.setFixedXSequence(true)
        table.config.setFixedYSequence(true)
        table.config.setSequenceGridStyle(LineStyle(0.1f, R.color.white))
        table.config.setColumnTitleGridStyle(LineStyle(0.1f, R.color.white))
        table.config.setShowXSequence(false)
        table.config.verticalPadding = 0
        table.config.contentGridStyle = LineStyle()
//        table.config.setMinTableWidth(60)

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

        var tableData: ArrayTableData<Int> =
            ArrayTableData.create("", colName, infos, object : IDrawFormat<Int> {
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

                    paint.setStyle(Paint.Style.FILL)
                    paint.setColor(
                        ContextCompat.getColor(
                            this@VenueSelectionActivity,
                            R.color.text_gray
                        )
                    )


                    //为不可预订时的处理
                    var bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_prohibit)
                    var zoomX = PhoneInfo.getPhonDensity(this@VenueSelectionActivity.application)
                    var widthM = (80f * zoomX - bitmap.width) / 2
                    var heightM = (40f * zoomX - bitmap.height) / 2

                    var paintBitmap = Paint()
                    var paintBackground = Paint()
                    //text
                    var paintText = Paint()

                    paintBitmap.isAntiAlias = true
                    paintBackground.isAntiAlias = true
                    paintText.isAntiAlias = true

                    //进行状态判断  0 未预定 ， 1 已预订，
                    when (infos[cellInfo.col]!![cellInfo.row]) {
                        1 -> {
                            paintBackground.style = Paint.Style.FILL

                            paintBackground.setColor(Color.parseColor("#e4e4e4"))
                            c.drawRoundRect(
                                (rect.left + 5).toFloat(),
                                (rect.top + 5).toFloat(),
                                (rect.right - 5).toFloat(),
                                (rect.bottom - 5).toFloat(),
                                8f, 8f,
                                paintBackground
                            )

                            c.drawBitmap(
                                bitmap,
                                (rect.left + widthM),
                                (rect.top + heightM),
                                paintBitmap
                            )
                        }
                        0 -> {
                            LogUtil.debugInfo("绘画次数 ")

                            paintText.textSize = 13f * zoomX
                            var w = paintText.measureText("¥50")
                            paintText.setColor(Color.parseColor("#3399fe"))

                            paintBackground.style = Paint.Style.STROKE
                            paintBackground.setColor(Color.parseColor("#3399fe"))
                            paintBackground.strokeWidth = 2f
                            c.drawRoundRect(
                                (rect.left + 5).toFloat(),
                                (rect.top + 5).toFloat(),
                                (rect.right - 5).toFloat(),
                                (rect.bottom - 5).toFloat(),
                                8f, 8f,
                                paintBackground
                            )

                            c.drawText(
                                "¥"+money[cellInfo.col]!![cellInfo.row],
                                rect.centerX().toFloat() - w / 2,
                                rect.centerY()
                                    .toFloat() - (paintText.ascent() + paintText.descent()) / 2,
                                paintText
                            )

                        }
                        2 -> {
                            paintBackground.style = Paint.Style.FILL
                            paintBackground.setColor(Color.parseColor("#3399fe"))

                            paintBackground.strokeWidth = 2f
                            c.drawRoundRect(
                                (rect.left + 5).toFloat(),
                                (rect.top + 5).toFloat(),
                                (rect.right - 5).toFloat(),
                                (rect.bottom - 5).toFloat(),
                                8f, 8f,
                                paintBackground
                            )
                            paintText.textSize = 13f * zoomX
                            var w = paintText.measureText("¥50")
                            paintText.setColor(Color.parseColor("#ffffff"))

                            c.drawText(
                                "¥"+money[cellInfo.col]!![cellInfo.row],
                                rect.centerX().toFloat() - w / 2,
                                rect.centerY()
                                    .toFloat() - (paintText.ascent() + paintText.descent()) / 2,
                                paintText
                            )

                        }
                        else -> {
//                            paintBackground.style = Paint.Style.FILL
//                            paintBackground.setColor(Color.parseColor("#3399fe"))
//
//                            paintBackground.strokeWidth = 2f
//                            c.drawRoundRect(
//                                (rect.left + 5).toFloat(),
//                                (rect.top + 5).toFloat(),
//                                (rect.right - 5).toFloat(),
//                                (rect.bottom - 5).toFloat(),
//                                8f, 8f,
//                                paintBackground
//                            )
//                            paintText.textSize = 13f * zoomX
//                            var w = paintText.measureText("¥50")
//                            paintText.setColor(Color.parseColor("#ffffff"))
//
//                            c.drawText(
//                                "¥50",
//                                rect.centerX().toFloat() - w / 2,
//                                rect.centerY()
//                                    .toFloat() - (paintText.ascent() + paintText.descent()) / 2,
//                                paintText
//                            )

                        }


                    }


//                    paintBackground.setColor(Color.parseColor("#3399fe"))
//                    paintBackground.style = Paint.Style.STROKE
//                    paintBackground.strokeWidth = 2f
//                    paintBackground.isAntiAlias = true

                }
            })

        tableData.onItemClickListener = object : TableData.OnItemClickListener<Int> {
            override fun onClick(column: Column<Int>, value: String, t: Int, col: Int, row: Int) {
                val status = tableData.data[col][row]
                /**
                 * @param status 0 未预定 ， 1 已预订，  2  完全不可选
                 */
                when (status) {
                    0 -> {
                        tableData.data[col][row] = 2
//                        tableData. = 2
                        mapDuplicate.set(
                            venueInfoList.get(col).list.get(row).id,
                            venueInfoList.get(col).list.get(row)
                        )
                        arrayTwoDiemn.add(venueInfoList.get(col).list.get(row))
//                        adapter.setData(arrayTwoDiemn)
                        //
                        updateTableDataAndRV(arrayTwoDiemn)
//                        table.notifyDataChanged()
                    }
                    1 -> {


                    }
                    2 -> {
//                        if(mapDuplicate.containsKey(venueInfoList.get(col).list.get(row).id)){
//                            mapDuplicate.remove(venueInfoList.get(col).list.get(row).id)
//                            val keys = mapDuplicate.keys
//                            val iter = keys.iterator()
//                            val list = ArrayList<SingleTimeInterval?>()
//                            while(iter.hasNext()){
//                                list.add(mapDuplicate.get(iter.next()))
//
//                            }
                        tableData.data[col][row] = 0
                        table.notifyDataChanged()
                        val length = arrayTwoDiemn.size
                        for (i in 0 until length) {
                            if (arrayTwoDiemn.get(i).id == venueInfoList.get(col).list.get(row).id) {
                                arrayTwoDiemn.removeAt(i)
                                break
                            }

                        }
//                        adapter.setData(arrayTwoDiemn)
                        updateTableDataAndRV(arrayTwoDiemn)

//                        }


                    }
                }
                //去掉数据显示提示
//                Toast.makeText(
//                    this@VenueSelectionActivity,
//                    "列:" + col + " 行：" + row + "数据：" + value,
//                    Toast.LENGTH_SHORT
//                ).show()
            }

        }

        tableData.ySequenceFormat = object : ISequenceFormat {
            override fun format(t: Int?): String {
                when (t) {

                    0 -> return "场地"

                    else -> {


                        //TODO 返回时间列表
                        var s =
                            StringBuilder().append("09:00")
                                .append("\n").append("-").append("\n")
                                .append("11:00")
                        return s.toString()
                    }

                }
            }


            override fun draw(canvas: Canvas?, sequence: Int, rect: Rect?, config: TableConfig?) {
                var t = format((sequence))
                //字体缩放
                val paint = config!!.paint
                val zoom: Float = if (config.getZoom() > 1) 1f else config.getZoom()
                paint.textSize = paint.textSize * zoom
                paint.textAlign = Paint.Align.CENTER

                val paint1 = config!!.paint

                paint.textSize = paint.textSize * zoom
                paint.textAlign = Paint.Align.CENTER

                val paint2 = config!!.paint

                paint.textSize = paint.textSize * zoom
                paint.textAlign = Paint.Align.CENTER


                var paint3 = TextPaint()
                paint3.isAntiAlias = true
                paint3.textAlign = Paint.Align.CENTER
//                paint1.textSize = 25 * zoom
                paint3.setColor(Color.parseColor("#333333"))
//                paint1.setColor(Color.parseColor("#3399fe"))
                val staticLayout1 = StaticLayout(
                    t, paint3, 50,
                    Layout.Alignment.ALIGN_CENTER, 1f, 0f, false
                )

//                staticLayout1.toString()
                if (sequence != 0 && sequence < 14) {
                    val textArray = format((sequence)).split("\n")
                    canvas!!.drawText(
                        textArray[0].toString(),
                        rect!!.centerX().toFloat(),
                        rect!!.centerY() + ((paint.descent() + paint.ascent())),
                        paint
                    )
                    canvas!!.drawText(
                        textArray[1].toString(),
                        rect!!.centerX().toFloat(),
                        rect!!.centerY() - ((paint1.descent() + paint1.ascent()) / 2),
                        paint1
                    )

                    canvas!!.drawText(
                        textArray[2].toString(),
                        rect!!.centerX().toFloat(),
                        rect!!.centerY() + ((paint2.descent() - paint2.ascent()) + 5),
                        paint2
                    )

                } else {
                    canvas!!.drawText(
                        format((sequence)), rect!!.centerX().toFloat(), DrawUtils.getTextCenterY(
                            rect!!.centerY(), paint
                        ), paint
                    )
                }
            }

        }


        table.setTableData(tableData)
    }

    override fun initData() {
        mViewModel.getVenueSelectionData(
            id,
            time,
            object : OnResponseListener<MutableList<SingleVenue>> {
                override fun onError(msg: String) {
                    LogUtil.debugInfo("t.onError" + msg)

                }

                override fun onException(msg: String) {
                    LogUtil.debugInfo("t.onException" + msg)

                }

                override fun onMsg(msg: String) {
                    LogUtil.debugInfo("msg" + msg)

                }

                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResult(t: MutableList<SingleVenue>) {
                    LogUtil.debugInfo("msg" + t)
                    venueInfoList.clear()
                    venueInfoList.addAll(t)
                    dataSquence(t)
                }
            })
    }

    /**
     * 对数据进行重排序
     * 组装成table组件需要的数据格式
     *
     *
     * */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun dataSquence(t: MutableList<SingleVenue>) {

        val newList: List<SingleVenue> =
            t.stream().sorted(Comparator.comparing(SingleVenue::sequence))
                .collect(Collectors.toList())
        //暂时去掉
//        originData = newList

        var venueNum = t.size
        var timeintervalNum = t.get(0).list.size
        var newInfoC = arrayOfNulls<Array<Int?>>(venueNum)

        var newInfoR = arrayOfNulls<Int>(timeintervalNum)

        var newMoneyC = arrayOfNulls<Array<Int?>>(venueNum)
        var newMoneyR = arrayOfNulls<Int>(timeintervalNum)

        var newColName = arrayOfNulls<String>(venueNum)

        for (i in 0 until venueNum) {
            newColName.set(i, i.toString())
            for (j in 0 until timeintervalNum) {
                if (j == 0) {
                    newMoneyR = Array(timeintervalNum, { 0 })
                    newInfoR = Array(timeintervalNum, { 0 })

                    newMoneyR.set(j, newList[i].list[j].price)
                    newInfoR.set(j, newList[i].list[j].isReserve)

                } else {
                    newMoneyR.set(j, newList[i].list[j].price)
                    newInfoR.set(j, newList[i].list[j].isReserve)
                }

            }
            newMoneyC.set(i, newMoneyR)
            newInfoC.set(i, newInfoR)
        }
//        infoss = newInfoC
//        newInfoC = arrayOf(arrayOf(0), arrayOf(0), arrayOf(0))

        inittable(table, newColName!!, newInfoC,newMoneyC)

    }


    //更新已选场次数据并计算场次和价格
    private fun updateTableDataAndRV(arrayTwoDiemn: MutableList<SingleTimeInterval>) {
        var sum: Float = 0f
        adapter.setData(arrayTwoDiemn)
        tvSelNum.setText("已选场次(" + arrayTwoDiemn.size + ")")
        for (i in 0 until arrayTwoDiemn.size) {
            sum += arrayTwoDiemn.get(i).price
        }
        tvTotalMoney.setText(SpannerableStringUtil.genStrForBtnCommit("订单金额:" +sum))
    }


    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

    override fun createObserver() {
    }
}