package com.gikoko.icon

import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RadialGradient
import android.graphics.RectF
import android.graphics.Shader

object KanotFrame {

    private val rim = Paint(Paint.ANTI_ALIAS_FLAG)
    private val hair = Paint(Paint.ANTI_ALIAS_FLAG)
    private val fill = Paint(Paint.ANTI_ALIAS_FLAG)
    private val curl = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect = RectF()

    init {
        rim.style = Paint.Style.STROKE
        hair.style = Paint.Style.STROKE
        fill.style = Paint.Style.FILL
        curl.style = Paint.Style.STROKE
        curl.strokeCap = Paint.Cap.ROUND
    }

    private fun shaders(u: Float, alpha: Int) {
        val s = LinearGradient(0f, 0f, 100f * u, 100f * u,
            intArrayOf(Ui.GOLD_1, Ui.GOLD_2, Ui.GOLD_3, Ui.GOLD_2, Ui.GOLD_1),
            floatArrayOf(0f, 0.3f, 0.6f, 0.85f, 1f),
            Shader.TileMode.CLAMP)
        rim.shader = s
        fill.shader = s
        curl.shader = s
        hair.color = 0xE6FFE9A3.toInt()
        rim.alpha = alpha
        hair.alpha = alpha
        fill.alpha = alpha
        curl.alpha = alpha
    }

    private fun curlPath(u: Float): Path {
        val p = Path()
        p.moveTo(28f * u, 9f * u)
        p.cubicTo(17f * u, 9f * u, 9f * u, 17f * u, 9f * u, 28f * u)
        p.moveTo(28f * u, 9f * u)
        p.cubicTo(20f * u, 9.5f * u, 14.5f * u, 15f * u, 14f * u, 21f * u)
        p.cubicTo(13.6f * u, 25.5f * u, 17f * u, 28.5f * u, 21f * u, 27.8f * u)
        p.cubicTo(24.8f * u, 27.1f * u, 25.3f * u, 23f * u, 22.2f * u, 21.6f * u)
        return p
    }

    private fun lotusPath(u: Float): Path {
        val p = Path()
        p.moveTo(0f, 8f * u)
        p.cubicTo(-3.5f * u, 5f * u, -3.5f * u, 1.5f * u, 0f, -1.5f * u)
        p.cubicTo(3.5f * u, 1.5f * u, 3.5f * u, 5f * u, 0f, 8f * u)
        p.close()
        p.moveTo(-6.5f * u, 7f * u)
        p.cubicTo(-9f * u, 4.5f * u, -8.5f * u, 1.5f * u, -6f * u, 0f)
        p.cubicTo(-3.8f * u, 2.5f * u, -3.4f * u, 5.5f * u, -4.8f * u, 7.6f * u)
        p.close()
        p.moveTo(6.5f * u, 7f * u)
        p.cubicTo(9f * u, 4.5f * u, 8.5f * u, 1.5f * u, 6f * u, 0f)
        p.cubicTo(3.8f * u, 2.5f * u, 3.4f * u, 5.5f * u, 4.8f * u, 7.6f * u)
        p.close()
        return p
    }

    private fun drawDiamond(c: Canvas, x: Float, y: Float, u: Float) {
        val save = c.save()
        c.translate(x, y)
        c.rotate(45f)
        rect.set(-3.4f * u, -3.4f * u, 3.4f * u, 3.4f * u)
        c.drawRect(rect, fill)
        c.restoreToCount(save)
    }

    fun draw(c: Canvas, u: Float, alpha: Int = 255) {
        shaders(u, alpha)
        rim.strokeWidth = 4f * u
        hair.strokeWidth = 1.4f * u
        curl.strokeWidth = 2.4f * u

        rect.set(4f * u, 4f * u, 96f * u, 96f * u)
        c.drawRoundRect(rect, 24f * u, 24f * u, rim)
        rect.set(10.5f * u, 10.5f * u, 89.5f * u, 89.5f * u)
        c.drawRoundRect(rect, 17.5f * u, 17.5f * u, hair)

        val save = c.save()
        var i = 0
        while (i < 4) {
            c.rotate(90f * i, 50f * u, 50f * u)
            c.drawPath(curlPath(u), curl)
            i++
        }
        c.restoreToCount(save)

        c.save()
        c.translate(50f * u, 4f * u)
        c.drawPath(lotusPath(u), fill)
        c.restore()

        drawDiamond(c, 4f * u, 50f * u, u)
        drawDiamond(c, 96f * u, 50f * u, u)
    }

    fun backing(c: Canvas, u: Float) {
        val bg = Paint(Paint.ANTI_ALIAS_FLAG)
        bg.shader = RadialGradient(50f * u, 22f * u, 75f * u,
            0x33120E22.toInt(), 0x66120E22.toInt(), Shader.TileMode.CLAMP)
        rect.set(11.5f * u, 11.5f * u, 88.5f * u, 88.5f * u)
        c.drawRoundRect(rect, 18f * u, 18f * u, bg)
    }
}
