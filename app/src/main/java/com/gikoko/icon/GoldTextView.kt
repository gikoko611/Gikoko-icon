package com.gikoko.icon

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Shader
import android.widget.TextView

class GoldTextView(context: Context) : TextView(context) {

    override fun onDraw(canvas: Canvas) {
        val w = width
        if (w > 0) {
            paint.shader = LinearGradient(0f, 0f, w * 0.65f, 0f,
                intArrayOf(Ui.GOLD_4, Ui.GOLD_2, 0xFFFFF3C4.toInt(), Ui.GOLD_1, Ui.GOLD_2, Ui.GOLD_4),
                floatArrayOf(0f, 0.2f, 0.45f, 0.6f, 0.8f, 1f),
                Shader.TileMode.CLAMP)
        }
        super.onDraw(canvas)
        paint.shader = null
    }
}
