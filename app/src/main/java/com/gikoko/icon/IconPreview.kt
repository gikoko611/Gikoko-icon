package com.gikoko.icon

import android.content.Context
import android.graphics.Canvas
import android.view.View

class IconPreview(context: Context) : View(context) {
    var app: AppInfo? = null
    var style: Int = 0

    override fun onDraw(c: Canvas) {
        val a = app ?: return
        val s = Math.min(width, height).toFloat()
        if (s <= 0f) return
        val u = s / 100f
        KanotFrame.draw(c, u, 255)
        if (style == 1) {
            AppGlyphs.draw(c, a, u)
        } else {
            KanotFrame.backing(c, u)
            val ic = a.icon
            if (ic != null) {
                ic.setBounds((22f * u).toInt(), (22f * u).toInt(), (78f * u).toInt(), (78f * u).toInt())
                ic.draw(c)
            }
        }
    }
}
