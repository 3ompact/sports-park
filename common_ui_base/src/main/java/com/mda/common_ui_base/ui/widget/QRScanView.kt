package com.mda.common_ui_base.ui.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import com.mda.basics_lib.utils.PhoneInfo


class QRScanView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val TAG: String = "QRScanView"
    private lateinit var mPaint: Paint
    private var topValue: Float = 100f
    private var bottomValueCurr: Float = 100f


    private var bottomValue: Float = 540f
    private var widthPhone: Float = 100f
    private var heightPhone: Float = 100f

    private lateinit var shader: LinearGradient
    private lateinit var shader1: LinearGradient
    private lateinit var shader2: LinearGradient


    var valueAnimator = ValueAnimator()

    init {
        var array = PhoneInfo.getPhoneWidthAndHeightPx(context.applicationContext)
        widthPhone = array[0].toFloat()
        Log.i("3ompact", "widthPhone" + widthPhone)

        heightPhone = array[1].toFloat()
        if (heightPhone > 1000) {
            topValue = 200f
            bottomValue = 900f
        } else {
            topValue = 100f
            bottomValue = 640f
        }


        shader = LinearGradient(
            widthPhone/2,
            topValue,
            widthPhone/2,
            topValue + 60,
            Color.parseColor("#00000000"),
            Color.parseColor("#5CBD8F"),
            Shader.TileMode.CLAMP
        )

        shader1 = LinearGradient(
            0f,
            topValue,
            widthPhone/2,
            topValue + 60,
            Color.parseColor("#00000000"),
            Color.parseColor("#5CBD8F"),
            Shader.TileMode.CLAMP
        )


        shader2 = LinearGradient(
            widthPhone / 2,
            topValue,
            widthPhone / 2,
            topValue + 60,
            Color.parseColor("#00000000"),
            Color.parseColor("#5CBD8F"),
            Shader.TileMode.CLAMP
        )


        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.color = Color.parseColor("#44B549")
        val lightingColorFilter: ColorFilter = LightingColorFilter(0xffffff, 0x003000)

        val shader3: Shader = ComposeShader(shader, shader, PorterDuff.Mode.SRC)
        val shader4: Shader = ComposeShader(shader3, shader2, PorterDuff.Mode.SRC_OVER)

        mPaint.setShader(shader3)
//        mPaint.setDither(true)
//        mPaint.setColorFilter(lightingColorFilter)
//        mPaint.setMaskFilter(BlurMaskFilter(20f, BlurMaskFilter.Blur.NORMAL))
//        gstart()
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var rect = RectF(0f, topValue, widthPhone, topValue + 60)
//        canvas!!.drawRect(rect,mPaint)  223f, 307f  132.13f  53.9485f, 126.0515f  10.5496  34.112f
//        canvas!!.drawArc(0f, topValue, widthPhone, topValue+60, 42.944f, 94.112f, false, mPaint)
//        canvas!!.drawOval(0f, topValue, widthPhone, topValue+60, mPaint)  72.944f,34.122f

//        canvas!!.drawRect(rect, mPaint)
        canvas!!.drawArc(rect, 180f, 180f, false, mPaint)
//        canvas!!.drawBitmap()
    }

    fun gstart() {
        valueAnimator.setFloatValues(0.0f, 25.0f)
        valueAnimator.setDuration(6000)
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.setInterpolator(LinearInterpolator())
        valueAnimator.addUpdateListener {
            var i = it.getAnimatedValue() as Float
            Log.i("3ompact", "topValue" + topValue)

            if (bottomValue == 640f && topValue < 620) {
                topValue = 100 + (20 * i)
                Log.i("3ompact", "x------" + i + "___" + topValue)
            } else {
                topValue = 100f
            }
            invalidate()
        }
        valueAnimator.start()
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop(){
        valueAnimator.cancel()

    }
}