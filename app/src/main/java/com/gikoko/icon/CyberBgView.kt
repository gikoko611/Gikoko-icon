package com.gikoko.icon

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.Shader
import android.os.SystemClock
import android.view.View

class CyberBgView(context: Context) : View(context) {

    private val bgPaint = Paint()
    private val line = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dot = Paint(Paint.ANTI_ALIAS_FLAG)
    private val glow = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        line.style = Paint.Style.STROKE
        line.strokeWidth = 1f
        dot.style = Paint.Style.FILL
        glow.style = Paint.Style.FILL
    }

    override fun onDraw(c: Canvas) {
        val w = width.toFloat()
        val h = height.toFloat()
        if (w <= 0f || h <= 0f) return
        val t = (SystemClock.uptimeMillis() % 24000L) / 24000f

        bgPaint.shader = LinearGradient(0f, 0f, 0f, h,
            intArrayOf(Ui.INK, Ui.INK_2, Ui.INK),
            null, Shader.TileMode.CLAMP)
        c.drawRect(0f, 0f, w, h, bgPaint)
        glow.shader = RadialGradient(w * 0.5f, h * 0.12f, w * 0.9f,
            0x2DF3C14B.toInt(), 0x00000000.toInt(), Shader.TileMode.CLAMP)
        c.drawRect(0f, 0f, w, h, glow)

        // faint kanot medallion watermarks
        val s1 = c.save()
        c.translate(w * 0.06f, h * 0.05f)
        KanotFrame.draw(c, w * 0.34f, 26)
        c.restoreToCount(s1)
        val s2 = c.save()
        c.translate(w * 0.62f, h * 0.30f)
        KanotFrame.draw(c, w * 0.17f, 16)
        c.restoreToCount(s2)

        // scrolling horizon lines
        line.shader = null
        line.color = 0x1A35E0FF.toInt()
        val step = h / 14f
        val off = t * step
        var i = 0
        while (i < 16) {
            val y = h * 0.55f + i * step + off
            if (y < h) c.drawLine(0f, y, w, y, line)
            i++
        }

        // floating gold particles
        var k = 0
        while (k < 30) {
            val seed = k * 37.7f
            val x = ((seed * 13.1f) % w + w) % w
            val span = h * (0.5f + (k % 3) * 0.25f)
            val y = h - (((t + k * 0.037f) % 1f) * span)
            dot.color = if (k % 3 == 0) 0x99F3C14B.toInt() else 0x59D4A017.toInt()
            c.drawCircle(x, y, if (k % 4 == 0) 3.5f else 2f, dot)
            k++
        }
        postInvalidateOnAnimation()
    }
}
