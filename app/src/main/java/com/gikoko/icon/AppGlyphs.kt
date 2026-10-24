package com.gikoko.icon

import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.Typeface

object AppGlyphs {

    const val PHONE = 1
    const val MSG = 2
    const val CAM = 3
    const val BROWSER = 4
    const val PLAY = 5
    const val PHOTOS = 6
    const val CLOCK = 7
    const val CALC = 8
    const val CAL = 9
    const val GEAR = 10
    const val FOLDER = 11
    const val MUSIC = 12
    const val MAIL = 13
    const val VIDEO = 14
    const val GENERIC = 15

    private val fill = Paint(Paint.ANTI_ALIAS_FLAG)
    private val stroke = Paint(Paint.ANTI_ALIAS_FLAG)
    private val back = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect = RectF()

    fun kindFor(pkg: String, label: String): Int {
        val p = pkg.lowercase()
        val l = label.lowercase()
        if (p.contains("camera") || l.contains("camera")) return CAM
        if (p.contains("phone") || p.contains("dial") || l.contains("phone")) return PHONE
        if (p.contains("message") || p.contains("sms") || p.contains("mms") ||
            p.contains("chat") || p.contains("whats") || p.contains("telegram") ||
            p.contains("messenger")) return MSG
        if (p.contains("browser") || p.contains("chrome") || p.contains("firefox") || p.contains("opera")) return BROWSER
        if (p.contains("vending") || p.contains("play")) return PLAY
        if (p.contains("gallery") || p.contains("photo") || l.contains("photo")) return PHOTOS
        if (p.contains("clock") || p.contains("alarm")) return CLOCK
        if (p.contains("calc")) return CALC
        if (p.contains("calendar") || p.contains("agenda")) return CAL
        if (p.contains("setting")) return GEAR
        if (p.contains("file") || p.contains("document") || p.contains("download") ||
            p.contains("explorer") || p.contains("manager")) return FOLDER
        if (p.contains("mail")) return MAIL
        if (p.contains("music") || p.contains("audio") || p.contains("spotify") ||
            p.contains("sound") || p.contains("player")) return MUSIC
        if (p.contains("video") || p.contains("movie") || p.contains("youtube") ||
            p.contains("netflix") || p.contains("vlc")) return VIDEO
        return GENERIC
    }

    fun draw(c: Canvas, app: AppInfo, u: Float) {
        back.shader = null
        back.color = Ui.BG_CARD
        rect.set(12f * u, 12f * u, 88f * u, 88f * u)
        c.drawRoundRect(rect, 20f * u, 20f * u, back)

        val s = LinearGradient(20f * u, 20f * u, 80f * u, 80f * u,
            intArrayOf(Ui.GOLD_1, Ui.GOLD_2, Ui.GOLD_3),
            null, Shader.TileMode.CLAMP)
        fill.shader = s
        fill.style = Paint.Style.FILL
        stroke.shader = s
        stroke.style = Paint.Style.STROKE
        stroke.strokeCap = Paint.Cap.ROUND
        stroke.strokeJoin = Paint.Join.ROUND
        stroke.strokeWidth = 4f * u

        when (kindFor(app.pkg, app.label)) {
            PHONE -> phone(c, u)
            MSG -> msg(c, u)
            CAM -> cam(c, u)
            BROWSER -> browser(c, u)
            PLAY -> play(c, u)
            PHOTOS -> photos(c, u)
            CLOCK -> clock(c, u)
            CALC -> calc(c, u)
            CAL -> cal(c, u)
            GEAR -> gear(c, u)
            FOLDER -> folder(c, u)
            MUSIC -> music(c, u)
            MAIL -> mail(c, u)
            VIDEO -> video(c, u)
            else -> generic(c, app, u)
        }
    }

    private fun phone(c: Canvas, u: Float) {
        val p = Path()
        p.moveTo(33f * u, 26f * u)
        p.rCubicTo(-4f * u, 0f, -7f * u, 3f * u, -6f * u, 7f * u)
        p.rCubicTo(4f * u, 22f * u, 19f * u, 37f * u, 41f * u, 41f * u)
        p.rCubicTo(4f * u, 1f * u, 7f * u, -2f * u, 7f * u, -6f * u)
        p.rLineTo(0f, -7f * u)
        p.rCubicTo(0f, -3f * u, -2f * u, -6f * u, -5f * u, -7f * u)
        p.rLineTo(-8f * u, -2f * u)
        p.rCubicTo(-3f * u, -1f * u, -5f * u, 0f, -7f * u, 2f * u)
        p.rLineTo(-3f * u, 3f * u)
        p.rCubicTo(-5f * u, -3f * u, -9f * u, -7f * u, -12f * u, -12f * u)
        p.rLineTo(3f * u, -3f * u)
        p.rCubicTo(2f * u, -2f * u, 3f * u, -4f * u, 2f * u, -7f * u)
        p.rLineTo(-2f * u, -8f * u)
        p.rCubicTo(-1f * u, -3f * u, -4f * u, -5f * u, -7f * u, -5f * u)
        p.close()
        c.drawPath(p, fill)
    }

    private fun msg(c: Canvas, u: Float) {
        val p = Path()
        p.moveTo(50f * u, 24f * u)
        p.cubicTo(34f * u, 24f * u, 22f * u, 33f * u, 22f * u, 45f * u)
        p.cubicTo(22f * u, 52f * u, 26f * u, 58f * u, 33f * u, 62f * u)
        p.lineTo(30f * u, 75f * u)
        p.lineTo(45f * u, 66.5f * u)
        p.cubicTo(47f * u, 66.8f * u, 48.5f * u, 67f * u, 50f * u, 67f * u)
        p.cubicTo(66f * u, 67f * u, 78f * u, 58f * u, 78f * u, 46f * u)
        p.cubicTo(78f * u, 34f * u, 66f * u, 24f * u, 50f * u, 24f * u)
        p.close()
        c.drawPath(p, fill)
    }

    private fun cam(c: Canvas, u: Float) {
        rect.set(24f * u, 28f * u, 76f * u, 72f * u)
        c.drawRoundRect(rect, 9f * u, 9f * u, stroke)
        c.drawCircle(50f * u, 50f * u, 11f * u, stroke)
        c.drawCircle(50f * u, 50f * u, 4f * u, fill)
        rect.set(28f * u, 22f * u, 38f * u, 30f * u)
        c.drawRoundRect(rect, 2f * u, 2f * u, fill)
    }

    private fun browser(c: Canvas, u: Float) {
        c.drawCircle(50f * u, 50f * u, 26f * u, stroke)
        rect.set(38f * u, 20f * u, 62f * u, 80f * u)
        c.drawOval(rect, stroke)
        c.drawLine(24f * u, 50f * u, 76f * u, 50f * u, stroke)
        c.drawCircle(50f * u, 50f * u, 6f * u, fill)
    }

    private fun play(c: Canvas, u: Float) {
        val p = Path()
        p.moveTo(36f * u, 26f * u)
        p.lineTo(72f * u, 50f * u)
        p.lineTo(36f * u, 74f * u)
        p.close()
        c.drawPath(p, fill)
    }

    private fun photos(c: Canvas, u: Float) {
        val p = Path()
        p.moveTo(50f * u, 50f * u)
        p.cubicTo(40f * u, 38f * u, 42f * u, 24f * u, 50f * u, 20f * u)
        p.cubicTo(58f * u, 24f * u, 60f * u, 38f * u, 50f * u, 50f * u)
        p.close()
        val save = c.save()
        var i = 0
        while (i < 4) {
            c.rotate(90f * i, 50f * u, 50f * u)
            c.drawPath(p, fill)
            i++
        }
        c.restoreToCount(save)
        back.color = Ui.BG_CARD
        c.drawCircle(50f * u, 50f * u, 3.5f * u, back)
    }

    private fun clock(c: Canvas, u: Float) {
        c.drawCircle(50f * u, 50f * u, 27f * u, stroke)
        c.drawLine(50f * u, 50f * u, 50f * u, 30f * u, stroke)
        c.drawLine(50f * u, 50f * u, 63f * u, 57f * u, stroke)
        c.drawCircle(50f * u, 50f * u, 3f * u, fill)
    }

    private fun calc(c: Canvas, u: Float) {
        rect.set(27f * u, 22f * u, 73f * u, 78f * u)
        c.drawRoundRect(rect, 7f * u, 7f * u, stroke)
        rect.set(33f * u, 30f * u, 67f * u, 40f * u)
        c.drawRoundRect(rect, 2.5f * u, 2.5f * u, fill)
        var col = 0
        while (col < 3) {
            var row = 0
            while (row < 2) {
                c.drawCircle((38f + col * 12f) * u, (52f + row * 14f) * u, 3f * u, fill)
                row++
            }
            col++
        }
    }

    private fun cal(c: Canvas, u: Float) {
        rect.set(25f * u, 24f * u, 75f * u, 80f * u)
        c.drawRoundRect(rect, 6f * u, 6f * u, stroke)
        rect.set(25f * u, 24f * u, 75f * u, 38f * u)
        c.drawRoundRect(rect, 6f * u, 6f * u, fill)
        rect.set(32f * u, 18f * u, 40f * u, 28f * u)
        c.drawRoundRect(rect, 2f * u, 2f * u, fill)
        rect.set(60f * u, 18f * u, 68f * u, 28f * u)
        c.drawRoundRect(rect, 2f * u, 2f * u, fill)
        c.drawCircle(50f * u, 60f * u, 6f * u, fill)
    }

    private fun gear(c: Canvas, u: Float) {
        val save = c.save()
        var i = 0
        while (i < 8) {
            c.rotate(45f * i, 50f * u, 50f * u)
            rect.set(45f * u, 18f * u, 55f * u, 34f * u)
            c.drawRoundRect(rect, 3f * u, 3f * u, fill)
            i++
        }
        c.restoreToCount(save)
        c.drawCircle(50f * u, 50f * u, 17f * u, fill)
        back.color = Ui.BG_CARD
        c.drawCircle(50f * u, 50f * u, 7f * u, back)
    }

    private fun folder(c: Canvas, u: Float) {
        val p = Path()
        p.moveTo(20f * u, 36f * u)
        p.lineTo(40f * u, 36f * u)
        p.lineTo(48f * u, 44f * u)
        p.lineTo(80f * u, 44f * u)
        p.cubicTo(82.8f * u, 44f * u, 85f * u, 46.2f * u, 85f * u, 49f * u)
        p.lineTo(85f * u, 75f * u)
        p.cubicTo(85f * u, 77.8f * u, 82.8f * u, 80f * u, 80f * u, 80f * u)
        p.lineTo(20f * u, 80f * u)
        p.cubicTo(17.2f * u, 80f * u, 15f * u, 77.8f * u, 15f * u, 75f * u)
        p.lineTo(15f * u, 41f * u)
        p.cubicTo(15f * u, 38.2f * u, 17.2f * u, 36f * u, 20f * u, 36f * u)
        p.close()
        c.drawPath(p, fill)
    }

    private fun music(c: Canvas, u: Float) {
        val p = Path()
        p.moveTo(42f * u, 62f * u)
        p.lineTo(42f * u, 28f * u)
        p.lineTo(66f * u, 23f * u)
        p.lineTo(66f * u, 56f * u)
        c.drawPath(p, stroke)
        c.drawCircle(38f * u, 64f * u, 7f * u, fill)
        c.drawCircle(62f * u, 58f * u, 7f * u, fill)
    }

    private fun mail(c: Canvas, u: Float) {
        rect.set(22f * u, 32f * u, 78f * u, 70f * u)
        c.drawRoundRect(rect, 6f * u, 6f * u, stroke)
        val p = Path()
        p.moveTo(24f * u, 36f * u)
        p.lineTo(50f * u, 56f * u)
        p.lineTo(76f * u, 36f * u)
        c.drawPath(p, stroke)
    }

    private fun video(c: Canvas, u: Float) {
        rect.set(24f * u, 28f * u, 76f * u, 74f * u)
        c.drawRoundRect(rect, 8f * u, 8f * u, stroke)
        val p = Path()
        p.moveTo(42f * u, 39f * u)
        p.lineTo(42f * u, 63f * u)
        p.lineTo(64f * u, 51f * u)
        p.close()
        c.drawPath(p, fill)
    }

    private fun generic(c: Canvas, app: AppInfo, u: Float) {
        val p = Paint(fill)
        p.textSize = 46f * u
        p.typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
        p.textAlign = Paint.Align.CENTER
        var letter = app.label.trim()
        if (letter.isEmpty()) letter = "A"
        val ch = letter.substring(0, 1).uppercase()
        c.drawText(ch, 50f * u, 66f * u, p)
        c.drawCircle(50f * u, 22f * u, 3f * u, fill)
    }
}
