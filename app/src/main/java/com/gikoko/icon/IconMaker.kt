package com.gikoko.icon

import android.graphics.Bitmap
import android.graphics.Canvas

object IconMaker {

    fun build(app: AppInfo, style: Int, sizePx: Int): Bitmap {
        val bm = Bitmap.createBitmap(sizePx, sizePx, Bitmap.Config.ARGB_8888)
        val c = Canvas(bm)
        // small margin so launcher shape masks do not cut the kanot frame
        val u = sizePx / 112f
        val off = sizePx * 0.06f
        c.save()
        c.translate(off, off)
        KanotFrame.draw(c, u, 255)
        if (style == 1) {
            AppGlyphs.draw(c, app, u)
        } else {
            KanotFrame.backing(c, u)
            val ic = app.icon
            if (ic != null) {
                ic.setBounds((22f * u).toInt(), (22f * u).toInt(), (78f * u).toInt(), (78f * u).toInt())
                ic.draw(c)
            }
        }
        c.restore()
        return bm
    }
}
